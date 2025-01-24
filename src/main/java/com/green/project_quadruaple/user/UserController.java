package com.green.project_quadruaple.user;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.common.model.ResultResponse;
import com.green.project_quadruaple.user.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
@Tag(name = "사용자")
public class UserController {
    private final UserService userService;

    // 회원가입 요청
    @PostMapping("sign-up")
    @Operation(summary = "회원가입", description = "사진 파일 때문에 postman 사용")
    public ResponseEntity<?> signUpUser(@RequestPart(required = false) MultipartFile profilePic, @Valid @RequestPart UserSignUpReq p) {
        int result = userService.signUp(profilePic, p);

        if (result < 0) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ResponseWrapper<>(ResponseCode.BAD_GATEWAY.getCode(), 0));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), result));
    }

    //아이디 중복 체크
    @GetMapping("sign-up")
    @Operation(summary = "아이디 중복 체크")
    public ResponseEntity<?> checkDuplicatedEmail(@RequestParam String email) {
        boolean result = userService.checkDuplicatedEmail(email);
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), result));
    }

    //로그인
    @PostMapping("sign-in")
    @Operation(summary = "로그인")
    public ResponseEntity<?> signInUser(@RequestBody UserSignInReq req, HttpServletResponse response) {
        UserSignInRes res = userService.signIn(req, response);
        if (res == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseCode.NOT_FOUND.getCode());
        }

        // 로그인 성공 시 반환할 데이터 생성
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", ResponseCode.OK.getCode());
        responseBody.put("data", 200);  // 혹은 적절한 성공 데이터를 설정
        responseBody.put("userId", res.getUserId());
        responseBody.put("accessToken", res.getAccessToken());

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("access-token")
    @Operation(summary = "토큰")
    public ResponseEntity<?> getAccessToken(HttpServletRequest req) {
        try {
            String accessToken = userService.getAccessToken(req); // Access Token 발급
            return ResponseEntity.ok(accessToken);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Token has expired")) {
                // Refresh Token 만료 시 401 반환
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseCode.UNAUTHORIZED.getCode());
            } else if (e.getMessage().contains("AccessToken을 재발행 할 수 없습니다.")) {
                // Refresh Token이 없거나 유효하지 않을 때
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseCode.BAD_GATEWAY.getCode());
            }
            // 기타 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseCode.SERVER_ERROR.getCode());
        }
    }

    //마이페이지 조회
    @GetMapping("userInfo")
    @Operation(summary = "마이페이지 조회")
    public ResponseEntity<ResponseWrapper<UserInfoDto>> getUserInfo(@RequestParam long userId) {
        UserInfoDto userInfo = userService.infoUser(userId);

        if (userInfo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), userInfo));
    }

    //마이페이지 수정
    @PatchMapping()
    @Operation(summary = "마이페이지 수정")
    public ResponseEntity<?> updateUserInfo(@RequestPart(required = false) MultipartFile profilePic, @RequestPart @Valid UserUpdateReq p) {
        UserUpdateRes userUpdateRes = userService.patchUser(profilePic, p);
        if (userUpdateRes == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(ResponseCode.BAD_GATEWAY.getCode(), 0));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), userUpdateRes));
    }
}