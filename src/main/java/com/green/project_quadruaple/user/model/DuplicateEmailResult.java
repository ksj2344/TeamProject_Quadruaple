package com.green.project_quadruaple.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicateEmailResult {
    private int emailCount;
    private int userIdCount;
}

