package com.green.project_quadruaple.common.config.enumdata;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
    // 성공
    OK("200 성공"),

    // 인증 실패
    UNAUTHORIZED("401 인증만료"),
    // 권한 없음
    Forbidden("403 권한없음"),
    // 결제요청
    PAYMENT_REQUIRED("402 결제요청"),
    //잘못된 요청
    NOT_FOUND("404 잘못된 요청"),
    //규격 에러
    NOT_Acceptable("406 규격에러"),
    //서버 에러
    SERVER_ERROR("500 서버에러"),
    //잘못된 응답
    BAD_GATEWAY("502 잘못된 응답"),
    //응답시간만료
    GATEWAY_TIMEOUT("503 응답시간만료");


    private final String code;

}
