package com.green.project_quadruaple.expense;

import com.green.project_quadruaple.expense.model.req.DutchReq;
import com.green.project_quadruaple.expense.model.req.ExpenseDelReq;
import com.green.project_quadruaple.expense.model.req.ExpenseInsReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> getExpenses(@RequestParam("trip_id") long tripId){
        return expenseService.getExpenses(tripId);
    }

    //정산하기
    @GetMapping("dutch")
    @Operation(summary = "정산하기", description = "비용추가 누르면 나오는 페이지")
    public ResponseEntity<?> dutchExpenses(@RequestParam("total_price") int totalPrice,
                                           @RequestParam("trip_id") long tripId,
                                           @RequestParam(value = "except_users", required = false) List<Long> exceptUsers){
        DutchReq p = new DutchReq(totalPrice,tripId,exceptUsers);
        return expenseService.dutchExpenses(p);
    }

    //가계부 추가
    @PostMapping
    @Operation(summary = "가계부 입력", description = "비용목록에 한 칸 추가")
    public ResponseEntity<?> postExpenses(@RequestBody ExpenseInsReq p){
        return expenseService.insSamePrice(p);
    }

    //가계부 조회
    @GetMapping("select")
    @Operation(summary = "가계부 조회", description = "입력된 금액 1줄 보기")
    public ResponseEntity<?> selectExpenses(@RequestParam("de_id") long deId, @RequestParam("trip_id") long tripId){
        return expenseService.selectExpenses(deId,tripId);
    }

    //가계부 제외된 목록 보기
    @GetMapping("excepted")
    @Operation(summary = "제외된 유저들 불러오기", description = "해당 정산에서 이미 제외되었던 인원 불러오기")
    public ResponseEntity exceptedMember(@RequestParam ("de_id") long deId){
        return null;
    }

    //가계부 수정
    @PutMapping
    @Operation(summary = "가계부 수정", description = "금액 혹은 인원수정")
    public ResponseEntity<?> updateExpenses(){
        return null;
    }

    //가계부 삭제
    @DeleteMapping
    @Operation(summary = "가계부 삭제", description = "가계부 삭제, 본인만 삭제 가능")
    public ResponseEntity<?> delExpenses(@RequestBody ExpenseDelReq p){
        return expenseService.delExpenses(p);
    }
}
