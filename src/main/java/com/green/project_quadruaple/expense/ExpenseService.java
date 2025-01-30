package com.green.project_quadruaple.expense;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.expense.model.DeDto;
import com.green.project_quadruaple.expense.model.ExpenseSameReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseMapper expenseMapper;

    public ResponseEntity<ResponseWrapper<Long>> insSamePrice(ExpenseSameReq p){
        DeDto d=new DeDto(null, p.getForText());
        expenseMapper.insDe(d);
        long deId=d.getDeId();


        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),deId));
    }

}
