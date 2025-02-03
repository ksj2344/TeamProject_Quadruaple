package com.green.project_quadruaple.expense.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//가격 다르면 써야하는거..
@ToString
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ExpenseDto extends DeDto {
    private int price;
    private long userId;
    private long tripId;
}
