package com.green.project_quadruaple.expense;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.config.security.AuthenticationFacade;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.expense.model.dto.DeDto;
import com.green.project_quadruaple.expense.model.dto.DutchPaidUserDto;
import com.green.project_quadruaple.expense.model.req.DutchReq;
import com.green.project_quadruaple.expense.model.req.ExpenseSameReq;
import com.green.project_quadruaple.expense.model.res.DutchRes;
import com.green.project_quadruaple.expense.model.res.ExpensesRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseMapper expenseMapper;
    private final AuthenticationFacade authenticationFacade;

    //추가하기
    public ResponseEntity<ResponseWrapper<Long>> insSamePrice(ExpenseSameReq p){
        DeDto d=new DeDto(null, p.getForText());
        expenseMapper.insDe(d);
        Long deId=d.getDeId();
        if(deId==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));}
        p.setDeId(deId);
        log.info("service>p:{}",p);
        int result=expenseMapper.insPaidUserSamePrice(p);
        if(result==0){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),deId));
    }

    //정산하기
    public ResponseEntity<ResponseWrapper<DutchRes>> dutchExpenses(DutchReq p){
        List<DutchPaidUserDto> dutchPaidUserDtos=expenseMapper.selDutchUsers(p);
        int price=p.getTotalPrice()/dutchPaidUserDtos.size();
        for(DutchPaidUserDto dto:dutchPaidUserDtos){
            dto.setPrice(price);
        }
        DutchRes res=new DutchRes(p.getPaidFor(),p.getTotalPrice(),dutchPaidUserDtos);
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),res));
    }

    //가계부 보기
    public ResponseEntity<ResponseWrapper<ExpensesRes>> getExpenses(long tripId){
        long userId=authenticationFacade.getSignedUserId();
        ExpensesRes result= expenseMapper.getExpenses(tripId,userId);
        if(result==null){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),result));
    }
}
