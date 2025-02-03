package com.green.project_quadruaple.expense;

import com.green.project_quadruaple.expense.model.dto.DeDto;
import com.green.project_quadruaple.expense.model.dto.DutchPaidUserDto;
import com.green.project_quadruaple.expense.model.req.DutchReq;
import com.green.project_quadruaple.expense.model.req.ExpenseSameReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExpenseMapper {
    //가계부 개설
    void insDe(DeDto d);

    //같은 가격 가계부 insert
    int insPaidUserSamePrice (ExpenseSameReq p);

    List<DutchPaidUserDto> selDutchUsers (DutchReq p);
}
