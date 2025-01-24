package com.green.project_quadruaple.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserUpdateReq {
    private long userId;
    private String email;
    private String name;
    private String pw;
    private String newPw;
    private String checkPw;
    private String profilePic;
}
