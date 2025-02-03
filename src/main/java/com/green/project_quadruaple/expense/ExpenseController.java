package com.green.project_quadruaple.expense;

import com.green.project_quadruaple.expense.model.req.DutchReq;
import com.green.project_quadruaple.expense.model.req.ExpenseSameReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("expense")
@Tag(name = "가계부")
public class ExpenseController {
    private final ExpenseService expenseService;

    //가계부 보기
    @GetMapping
    @Operation(summary = "가계부 보기", description = "여행에 관련한 비용목록 보기")
    public ResponseEntity<?> getExpenses(){
        return null;
    }

    //정산하기
    @GetMapping("dutch")
    @Operation(summary = "정산하기", description = "비용추가 누르면 나오는 페이지")
    public ResponseEntity<?> dutchExpenses(@RequestBody DutchReq p){
        return expenseService.dutchExpenses(p);
    }

    //가계부 추가
    @PostMapping
    @Operation(summary = "가계부 입력", description = "비용목록에 한 칸 추가")
    public ResponseEntity<?> postExpenses(@RequestBody ExpenseSameReq p){
        return expenseService.insSamePrice(p);
    }
}
