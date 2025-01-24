package com.green.project_quadruaple.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.project_quadruaple.common.model.ResultResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserUpdateRes {
    @JsonIgnore
    private final long userId;
    private final String email;
    private final String pw;
}
