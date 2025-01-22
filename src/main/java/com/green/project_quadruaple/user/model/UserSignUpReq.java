package com.green.project_quadruaple.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.project_quadruaple.common.config.jwt.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
public class UserSignUpReq {
    @JsonIgnore
    private long userId;
    private String email;
    private String pw;
    private String name;
    @JsonIgnore
    private String profilePic;
    @JsonIgnore
    private int state;
    private List<UserRole> role = new ArrayList<>();
}
