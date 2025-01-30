package com.green.project_quadruaple.expense.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ExpenseSameReq extends DeDto{
    private int price;
    private long tripId;
    private List<Long> userIds;
}

