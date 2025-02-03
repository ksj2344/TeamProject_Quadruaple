package com.green.project_quadruaple.expense.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@EqualsAndHashCode
public class PaidUser {
    private long userId;
    private String name;
    private String profilePic;
}
