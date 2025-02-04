package com.green.project_quadruaple.expense.model.res;

import com.green.project_quadruaple.expense.model.dto.DutchPaidUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class DutchRes {
    private int totalPrice;
    private List<DutchPaidUserDto> paidUserList;
}
