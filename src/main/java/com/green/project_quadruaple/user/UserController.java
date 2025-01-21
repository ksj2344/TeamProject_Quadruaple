package com.green.project_quadruaple.user;

import com.green.secondprojectuser.common.ResultResponse;
import com.green.secondprojectuser.user.model.DuplicateEmailDao;
import com.green.secondprojectuser.user.model.UserSignInReq;
import com.green.secondprojectuser.user.model.UserSignInRes;
import com.green.secondprojectuser.user.model.UserSignUpReq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    // 이메일 중복 체크
    @GetMapping("check-email")
    public boolean checkEmail(@ParameterObject @ModelAttribute DuplicateEmailDao email) {
        return userService.checkEmail(email);
    }

    // 회원가입 요청
    @PostMapping("sign-up")
    public ResultResponse<Integer> postUser(@RequestPart(required = false) MultipartFile pic, @RequestPart UserSignUpReq p) {
        int result = userService.signUpWithEmailVerification(pic, p);

        if (result > 0) {
            return ResultResponse.<Integer>builder()
                    .resultMessage("200")
                    .resultData((int)p.getUserId())
                    .build();
        } else {
            return ResultResponse.<Integer>builder()
                    .resultMessage("회원가입에 실패했습니다.")
                    .resultData(result)
                    .build();
        }
    }

    // 이메일 인증 요청
    @GetMapping("signUpConfirm")
    public ResultResponse<String> signUpConfirm(@RequestParam String email, @RequestParam String authKey, HttpServletResponse response) {
        // 이메일 인증 처리 (authKey는 이메일 링크에 포함된 값이므로 비교만 수행)
        boolean isConfirmed = userService.confirmEmail(email, authKey);

        if (isConfirmed) {
            return ResultResponse.<String>builder()
                    .resultMessage("200")
                    .resultData("SUCCESS")
                    .build();
        } else {
            return ResultResponse.<String>builder()
                    .resultMessage("이메일 인증에 실패했습니다.")
                    .resultData("FAILURE")
                    .build();
        }
    }

    @PostMapping("sign-in")
    public UserSignInRes signIn(@RequestBody UserSignInReq req, HttpServletResponse response) {
        log.info("Sign in request: {}", req);
        return userService.signIn(req, response);
    }

    @GetMapping("access-token")
    public String getAccessToken(HttpServletRequest req) {
        return userService.getAccessToken(req);
    }
}
