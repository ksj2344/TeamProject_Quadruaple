package com.green.project_quadruaple.expense.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ExpenseDto extends DeDto{
    private int price;
    private long userId;
    private long tripId;
}
