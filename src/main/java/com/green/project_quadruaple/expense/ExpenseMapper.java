package com.green.project_quadruaple.expense;

import com.green.project_quadruaple.expense.model.dto.DeDto;
import com.green.project_quadruaple.expense.model.dto.DutchPaidUserDto;
import com.green.project_quadruaple.expense.model.req.DutchReq;
import com.green.project_quadruaple.expense.model.req.ExpenseInsReq;
import com.green.project_quadruaple.expense.model.res.ExpenseOneRes;
import com.green.project_quadruaple.expense.model.res.ExpensesRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExpenseMapper {
    //가계부 개설
    void insDe(DeDto d);
    //가계부 insert
    int insPaid (Map<String, Object> paramMap);
    //정산하기
    List<DutchPaidUserDto> selDutchUsers (DutchReq p);


    //가계부 보기
    ExpensesRes getExpenses(long tripId, long userId);

    //가계부 한줄 보기
    ExpenseOneRes selExpenseOne(long deId);

    //참여유저인지 확인하기
    boolean IsUserInTrip(long tripId, long userId);
}
