package com.green.project_quadruaple.user;

import com.green.project_quadruaple.common.MyFileUtils;
import com.green.project_quadruaple.common.config.CookieUtils;
import com.green.project_quadruaple.common.config.constant.JwtConst;
import com.green.project_quadruaple.common.config.jwt.JwtTokenProvider;
import com.green.project_quadruaple.common.config.jwt.JwtUser;
import com.green.project_quadruaple.common.config.jwt.UserRole;
import com.green.project_quadruaple.user.model.*;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final MyFileUtils myFileUtils;
    private final JavaMailSender mailSender;
    private final JwtTokenProvider jwtTokenProvider;
    private final CookieUtils cookieUtils;
    private final JwtConst jwtConst;
    private final Map<String, String> authKeyStore = new HashMap<>(); // 이메일과 인증 키 저장 (메모리 상)

    @Value("${spring.mail.username}")
    private static String FROM_ADDRESS;

    // 이메일 중복 체크
    public boolean checkEmail(DuplicateEmailDao email) {
        return userMapper.checkEmail(email);
    }

    // 회원가입 및 이메일 인증 메일 발송
    public int signUpWithEmailVerification(MultipartFile pic, UserSignUpReq p) {
        // 프로필 사진 처리
        String savedPicName = pic != null ? myFileUtils.makeRandomFileName(pic) : null;
        String hashedPassword = passwordEncoder.encode(p.getPw());
        p.setProfilePic(savedPicName);
        p.setPw(hashedPassword);

        // 사용자 정보 저장
        int result = userMapper.insUser(p);
        if (result <= 0) {
            return 0;
        }
        int resultRole = userMapper.insUserRole(p);

        // 프로필 사진 저장
        if (pic != null) {
            long userId = p.getUserId();
            String middlePath = String.format("user/%d", userId);
            myFileUtils.makeFolders(middlePath);

            String filePath = String.format("%s/%s", middlePath, savedPicName);
            try {
                myFileUtils.transferTo(pic, filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 이메일 인증 키 생성 및 저장
        String authKey = getAuthCode(6);
        authKeyStore.put(p.getEmail(), authKey); // 메모리에 저장

        // 이메일 인증 메일 발송
        String email = p.getEmail();
        String mailContent = "<h1>[이메일 인증]</h1><br><p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>"
                + "<a href='http://localhost:8080/api/user/signUpConfirm?email=" + email
                + "&authKey=" + authKey + "' target='_blenk'>이메일 인증 확인</a>";

        try {
            MimeMessage mail = mailSender.createMimeMessage();
            mail.setFrom(FROM_ADDRESS);
            mail.setSubject("회원가입 이메일 인증 ", "utf-8");
            mail.setText(mailContent, "utf-8", "html");
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mailSender.send(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return result;
    }

    // 이메일 인증 처리
    public boolean confirmEmail(String email, String authKey) {
        // 메모리에서 인증 키 확인
        String storedAuthKey = authKeyStore.get(email);
        if (storedAuthKey == null || !storedAuthKey.equals(authKey)) {
            return false;
        }

        // 인증 성공 시 state를 1로 업데이트
        int updatedRows = userMapper.updUser(email);
        if (updatedRows > 0) {
            authKeyStore.remove(email); // 인증 완료된 키 제거
            return true;
        }

        return false;
    }

    // 인증 코드 생성
    private String getAuthCode(int size) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
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
        userSelOne.setRoles(roles);

        // AT, RT
        JwtUser jwtUser = new JwtUser(userSelOne.getUserId(), userSelOne.getRoles());
        String accessToken = jwtTokenProvider.generateAccessToken(jwtUser);
        String refreshToken = jwtTokenProvider.generateRefreshToken(jwtUser);

        // RT를 쿠키에 담는다.
        cookieUtils.setCookie(response, jwtConst.getRefreshTokenCookieName(), refreshToken, jwtConst.getRefreshTokenCookieExpiry());

        return UserSignInRes.builder()
                .accessToken(accessToken)
                .userId(userSelOne.getUserId())
                .name(userSelOne.getName())
                .build();
    }

    public String getAccessToken(HttpServletRequest req) {
        Cookie cookie = Optional.ofNullable(cookieUtils.getCookie(req, jwtConst.getRefreshTokenCookieName()))
                .orElseThrow(() -> {
                    throw new RuntimeException("AccessToken을 재발행 할 수 없습니다.");
                });
        String refreshToken = cookie.getValue();
        JwtUser jwtUser = jwtTokenProvider.getJwtUserFromToken(refreshToken);
        return jwtTokenProvider.generateAccessToken(jwtUser);
    }
}
