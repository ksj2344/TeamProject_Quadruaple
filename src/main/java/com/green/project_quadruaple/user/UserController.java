package com.green.project_quadruaple.user;

import com.green.project_quadruaple.user.model.DuplicateEmailResult;
import com.green.project_quadruaple.user.model.UserSignInReq;
import com.green.project_quadruaple.user.model.UserSignInRes;
import com.green.project_quadruaple.user.model.UserSignUpReq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    // 회원가입 요청
    @PostMapping("sign-up")
    public int postUser(@RequestPart(required = false) MultipartFile pic, @Valid @RequestPart UserSignUpReq p) {
        int result = userService.signUpWithEmailVerification(pic, p);

        if (result > 0) {
            return 200;
        } else {
            return 400;
        }
    }

    // 이메일 인증 요청
    @GetMapping("signUpConfirm")
    public void signUpConfirm(@RequestParam String email, @RequestParam String authKey, HttpServletResponse response) throws IOException {
        // 이메일 인증 처리
        boolean isConfirmed = userService.confirmEmail(email, authKey);

        if (isConfirmed) {
            response.sendRedirect("/successPage.html"); // 인증 성공 시 리다이렉트
        } else {
            response.sendRedirect("/failurePage.html"); // 인증 실패 시 리다이렉트
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
