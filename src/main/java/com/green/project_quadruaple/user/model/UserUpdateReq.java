package com.green.project_quadruaple.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class UserUpdateReq {
    @JsonIgnore
    private final long userId;
    private final String email;
    private final String name;
    private final String pw;
    private final String newPw;
    private final String checkPw;
    private final String profilePic;
}
