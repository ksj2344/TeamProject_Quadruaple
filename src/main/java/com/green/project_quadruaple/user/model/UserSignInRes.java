package com.green.project_quadruaple.user.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSignInRes {
    private long userId;
    private String name;
    private String accessToken;
}
