package com.green.project_quadruaple.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Builder
@ToString
public class UserInfoDto {
    @JsonIgnore
    private final long userId;
    private final String email;
    private final String name;
    private final String profilePIc;
}
