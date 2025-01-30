package com.green.project_quadruaple.expense;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //가계부 추가
    @PostMapping
    @Operation(summary = "가계부 입력", description = "비용목록에 한 칸 추가")
    public ResponseEntity<?> postExpenses(){
        return null;
    }
}
