package com.green.project_quadruaple.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfo {
    private long userId;
    private String email;
    private String name;
    private String profilePIc;
}
