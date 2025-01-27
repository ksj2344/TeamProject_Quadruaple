package com.green.project_quadruaple.user;

import com.green.project_quadruaple.common.MyFileUtils;
import com.green.project_quadruaple.common.config.CookieUtils;
import com.green.project_quadruaple.common.config.constant.JwtConst;
import com.green.project_quadruaple.common.config.jwt.TokenProvider;
import com.green.project_quadruaple.common.config.jwt.JwtUser;
import com.green.project_quadruaple.common.config.jwt.UserRole;
import com.green.project_quadruaple.common.config.security.AuthenticationFacade;
import com.green.project_quadruaple.common.model.ResultResponse;
import com.green.project_quadruaple.user.mail.MailService;
import com.green.project_quadruaple.user.model.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final MyFileUtils myFileUtils;
    private final TokenProvider jwtTokenProvider;
    private final CookieUtils cookieUtils;
    private final AuthenticationFacade authenticationFacade;

    @Value("${spring.mail.username}")
    private static String FROM_ADDRESS;

    // 회원가입 및 이메일 인증 메일 발송
    public int signUp(MultipartFile pic, UserSignUpReq p) {
        String email = p.getEmail();

        //이메일 인증 여부
        if (!checkEmail(email)) {
            return 0;
        }

        // 이메일 중복 체크
        DuplicateEmailResult duplicateEmailResult = userMapper.getEmailDuplicateInfo(p);
        if (duplicateEmailResult.getEmailCount() > 0) {
            return 0;
        }

        // 프로필 사진 처리
        String savedPicName = pic != null ? myFileUtils.makeRandomFileName(pic) : null;
        String hashedPassword = passwordEncoder.encode(p.getPw());
        p.setProfilePic(savedPicName);
        p.setPw(hashedPassword);

        try {
            int result = userMapper.insUser(p);
            if (result > 0) {
                long userId = p.getUserId();
                p.setUserId(userId);
                userMapper.insUserRole(p);
            }
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

    public boolean checkDuplicatedEmail(String email) {
        boolean isDuplicated = userMapper.isEmailDuplicated(email);
        if (isDuplicated) {
            return false;
        }
        return true;
    }

    //-------------------------------------------------
    // 로그인
    @Transactional
    public UserSignInRes signIn(UserSignInReq req, HttpServletResponse response) {
        UserSelOne userSelOne = userMapper.selUserByEmail(req).orElseThrow(() -> {
            throw new RuntimeException("아이디를 확인해 주세요.");
        });

        if(!passwordEncoder.matches(req.getPw(), userSelOne.getPw())) {
            throw new RuntimeException("비밀번호를 확인해 주세요.");
        }

        List<UserRole> roles = new ArrayList<>(2);
        roles.add(UserRole.USER);
        roles.add(UserRole.ADMIN);
        roles.add(UserRole.BUIS);
        userSelOne.setRoles(roles);

        // AT, RT
        JwtUser jwtUser = new JwtUser(userSelOne.getUserId(), userSelOne.getRoles());
        String accessToken = jwtTokenProvider.generateToken(jwtUser, Duration.ofHours(6));
        String refreshToken = jwtTokenProvider.generateToken(jwtUser, Duration.ofDays(15));

        // RT를 쿠키에 담는다.
        // refreshToken은 쿠키에 담는다.
        int maxAge = 1_296_000; // 15 * 24 * 60 * 60 > 15일의 초(second) 값
        cookieUtils.setCookie(response, "refreshToken", refreshToken, maxAge);

        return UserSignInRes.builder()
                .accessToken(accessToken)
                .userId(userSelOne.getUserId())
                .name(userSelOne.getName())
                .build();
    }

    public String getAccessToken (HttpServletRequest req) {
        Cookie cookie = cookieUtils.getCookie(req,"refreshToken");
        String refreshToken = cookie.getValue();
        log.info("refreshToken: {}", refreshToken);

        JwtUser jwtUser = jwtTokenProvider.getJwtUserFromToken(refreshToken);
        String accessToken = jwtTokenProvider.generateToken(jwtUser, Duration.ofHours(8));

        return accessToken;
    }

    private boolean checkEmail(String email) {
        // 인증된 이메일이 아닐때, 인증 만료되었을때
        return MailService.mailChecked.getOrDefault(email, false);
    }

    //-------------------------------------------------
    // 마이페이지 조회
    public UserInfoDto infoUser() {
        long signedUserId = authenticationFacade.getSignedUserId();
        UserInfo userInfo = userMapper.selUserInfo(signedUserId);
        return UserInfoDto.builder()
                .signedUserId(signedUserId)
                .name(userInfo.getName())
                .email(userInfo.getEmail())
                .profilePIc(userInfo.getProfilePIc())
                .build();
    }

    //-------------------------------------------------
    // 마이페이지 수정
    public UserUpdateRes patchUser(MultipartFile profilePic, UserUpdateReq req) {
        long signedUserId = authenticationFacade.getSignedUserId();
        UserUpdateRes checkPassword = userMapper.checkPassword(signedUserId, req.getPw());

        if (checkPassword == null || !passwordEncoder.matches(req.getPw(), checkPassword.getPw())) {
            throw new IllegalArgumentException("잘못된 사용자 ID 또는 비밀번호입니다.");
        }

        if (req.getNewPw().equals(checkPassword.getPw())) {
            throw new IllegalArgumentException("새 비밀번호는 기존 비밀번호와 같을 수 없습니다.");
        }

        // newPw가 null이 아닌 경우에만 비밀번호 변경
        if (req.getNewPw() != null && !req.getNewPw().equals(checkPassword.getPw())) {
            String hashedPassword = passwordEncoder.encode(req.getNewPw());
            userMapper.changePassword(signedUserId, hashedPassword);
            req.setNewPw(hashedPassword);
        } else if (req.getNewPw() != null) {
            throw new IllegalArgumentException("새 비밀번호는 기존 비밀번호와 같을 수 없습니다.");
        }

        if (profilePic != null && !profilePic.isEmpty()) {
            String targetDir = "user/" + req.getEmail();
            myFileUtils.makeFolders(targetDir);

            String savedFileName = myFileUtils.makeRandomFileName(profilePic);

            // 기존 파일 삭제
            String deletePath = String.format("%s/user/%s", myFileUtils.getUploadPath(), req.getEmail());
            myFileUtils.deleteFolder(deletePath, false);

            // 파일 저장
            String filePath = String.format("%s/%s", targetDir, savedFileName);
            try {
                myFileUtils.transferTo(profilePic, filePath);
            } catch (IOException e) {
                throw new RuntimeException("프로필 사진 저장에 실패했습니다.", e);
            }
        }

        int result = userMapper.updUser(req);
        if (result == 0) {
            throw new RuntimeException("사용자 정보를 업데이트하는 데 실패했습니다.");
        }

        return UserUpdateRes.builder()
                .signedUserId(signedUserId)
                .pw(req.getNewPw())
                .build();
    }
}
