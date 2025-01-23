package com.green.project_quadruaple.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.project_quadruaple.common.model.ResultResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class UserInfoDto extends ResultResponse {
    @JsonIgnore
    private final long userId;
    private final String email;
    private final String name;
    private final String profilePIc;

    public UserInfoDto(String code, long userId, String name, String email, String profilePIc) {
        super(code);
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.profilePIc = profilePIc;
    }
}
