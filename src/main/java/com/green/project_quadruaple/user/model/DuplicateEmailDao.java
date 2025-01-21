package com.green.project_quadruaple.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicateEmailDao {
    @Schema(title = "중복체크", description = "중복 아님: false, 중복: true")
    private String email;
}
