package com.green.project_quadruaple.expense;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.config.jwt.JwtUser;
import com.green.project_quadruaple.common.config.security.AuthenticationFacade;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.expense.model.dto.DeDto;
import com.green.project_quadruaple.expense.model.dto.DutchPaidUserDto;
import com.green.project_quadruaple.expense.model.dto.UserPriceDto;
import com.green.project_quadruaple.expense.model.req.DutchReq;
import com.green.project_quadruaple.expense.model.req.ExpenseDelReq;
import com.green.project_quadruaple.expense.model.req.ExpenseInsReq;
import com.green.project_quadruaple.expense.model.res.ExpenseOneRes;
import com.green.project_quadruaple.expense.model.res.ExpensesRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseMapper expenseMapper;
    private final AuthenticationFacade authenticationFacade;

    //추가하기
    public ResponseEntity<ResponseWrapper<Long>> insSamePrice(ExpenseInsReq p){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof JwtUser) ||!expenseMapper.IsUserInTrip(p.getTripId(),authenticationFacade.getSignedUserId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponseWrapper<>(ResponseCode.Forbidden.getCode(), null));
        }
        DeDto d=new DeDto(null, p.getPaidFor());
        expenseMapper.insDe(d);
        Long deId=d.getDeId();
        if(deId==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));}
        List<UserPriceDto> dtos=p.getPriceList();
        List<Map<String, Object>> userPaid = new ArrayList<>();
        for(UserPriceDto u:dtos){
            Map<String, Object> map=new HashMap<>();
            map.put("price",u.getPrice());
            map.put("userId",u.getUserId());
            userPaid.add(map);
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("deId", deId);
        paramMap.put("tripId", p.getTripId());
        paramMap.put("userPaid", userPaid);
        log.info("service>paramMap:{}",paramMap);
        int result=expenseMapper.insPaid(paramMap);
        if(result==0){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),deId));
    }

    //정산하기
    public ResponseEntity<ResponseWrapper<List<DutchPaidUserDto>>> dutchExpenses(DutchReq p){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof JwtUser)||!expenseMapper.IsUserInTrip(p.getTripId(),authenticationFacade.getSignedUserId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponseWrapper<>(ResponseCode.Forbidden.getCode(), null));
        }
        if(p.getExceptUsers()==null){p.setExceptUsers(new ArrayList<>());}
        int totalPrice=p.getTotalPrice();
        List<DutchPaidUserDto> dutchPaidUserDtos=expenseMapper.selDutchUsers(p);
        int price = (int) (Math.round((double) totalPrice / dutchPaidUserDtos.size() / 10) * 10);
        for(DutchPaidUserDto dto:dutchPaidUserDtos){
            dto.setPrice(price);
        }
        if(price*dutchPaidUserDtos.size() != totalPrice){
            Random r=new Random();
            int morePrice=totalPrice-price*dutchPaidUserDtos.size()+price;
            dutchPaidUserDtos.get(r.nextInt(dutchPaidUserDtos.size())).setPrice(morePrice);
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),dutchPaidUserDtos));
    }

    //가계부 보기
    public ResponseEntity<ResponseWrapper<ExpensesRes>> getExpenses(long tripId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof JwtUser)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponseWrapper<>(ResponseCode.Forbidden.getCode(), null));
        }
        long userId=authenticationFacade.getSignedUserId();
        if(!expenseMapper.IsUserInTrip(tripId,userId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponseWrapper<>(ResponseCode.Forbidden.getCode(), null));
        }
        ExpensesRes result= expenseMapper.getExpenses(tripId,userId);
        if(result==null){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),result));
    }

    //가계부 한줄 보기
    public ResponseEntity<ResponseWrapper<ExpenseOneRes>> selectExpenses(long deId, long tripId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof JwtUser)||!expenseMapper.IsUserInTrip(tripId,authenticationFacade.getSignedUserId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponseWrapper<>(ResponseCode.Forbidden.getCode(), null));
        }
        ExpenseOneRes res=expenseMapper.selExpenseOne(deId);
        if(res==null){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), res));
    }

    //가계부 삭제하기
    @Transactional
    public ResponseEntity<ResponseWrapper<Integer>> delExpenses(ExpenseDelReq p){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof JwtUser)||!expenseMapper.IsUserInTrip(p.getTripId(),authenticationFacade.getSignedUserId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponseWrapper<>(ResponseCode.Forbidden.getCode(), null));
        }
        int res=expenseMapper.delExpenses(p.getDeId());
        if(res==0){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), res));
    }
}
