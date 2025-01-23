package com.green.project_quadruaple.user.model;

import com.green.project_quadruaple.common.model.ResultResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserSignInRes extends ResultResponse {
    private long userId;
    private String name;
    private String accessToken;

    public UserSignInRes(String code, long userId, String name, String accessToken) {
        super(code);
        this.userId = userId;
        this.name = name;
        this.accessToken = accessToken;
    }
}
