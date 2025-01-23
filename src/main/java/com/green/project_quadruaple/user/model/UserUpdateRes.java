package com.green.project_quadruaple.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.project_quadruaple.common.model.ResultResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserUpdateRes extends ResultResponse {
    @JsonIgnore
    private final long userId;
    private final String email;
    private final String pw;

    public UserUpdateRes(String code, long userId, String email, String pw) {
        super(code);
        this.userId = userId;
        this.email = email;
        this.pw = pw;
    }
}
