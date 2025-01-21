package com.green.project_quadruaple.common.model;


import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultRespons {
    private String code;

    // 성공
    @ResponseStatus(HttpStatus.OK)
    public static ResultRespons success() {
        return new ResultRespons(ResponseCode.OK.getCode());
    }

    // 권한 없음
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public static ResultRespons forbidden() {return new ResultRespons(ResponseCode.Forbidden.getCode());}

    // 잘못된 요청
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static ResultRespons notFound() {return new ResultRespons(ResponseCode.NOT_FOUND.getCode());}

    // 인증실패
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public static ResultRespons unauthorized() {
        return new ResultRespons(ResponseCode.UNAUTHORIZED.getCode());
    }

    // 결제요청
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    public static ResultRespons paymentRequired() {return new ResultRespons(ResponseCode.PAYMENT_REQUIRED.getCode());}

    // 규격에러
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public static ResultRespons notAcceptable() {
        return new ResultRespons(ResponseCode.NOT_Acceptable.getCode());
    }

    // 서버에러
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static ResultRespons severError() {
        return new ResultRespons(ResponseCode.SERVER_ERROR.getCode());
    }

    // 잘못된 응답
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public static ResultRespons badGateway() {
        return new ResultRespons(ResponseCode.BAD_GATEWAY.getCode());
    }

    // 잘못된 응답
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public static ResultRespons gatewayTimeout() {
        return new ResultRespons(ResponseCode.GATEWAY_TIMEOUT.getCode());
    }

}
