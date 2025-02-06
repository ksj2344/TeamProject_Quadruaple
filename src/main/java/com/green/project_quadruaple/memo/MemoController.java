package com.green.project_quadruaple.memo;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.memo.model.MemoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/memo")
@RequiredArgsConstructor
@Tag(name = "메모")
public class MemoController {

    private final MemoService memoService;

    @Operation(summary = "유저 여행 메모 확인")
    @GetMapping("/select")
    public ResponseEntity<?> selectMemo(@RequestParam Long memoId, Authentication authentication) {
        Long signedUserId = getUserIdFromAuthentication(authentication);
        MemoDto memo = memoService.getMemo(memoId, signedUserId);

        Map<String, Object> response = new HashMap<>();
        response.put("code", "200 성공");
        response.put("memo_id", memo.getMemoId());
        response.put("title", memo.getTitle());
        response.put("content", memo.getContent());
        response.put("date", memo.getUpdatedAt() == null ? memo.getCreatedAt() : memo.getUpdatedAt());

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "유저 여행 메모 추가")
    @PostMapping("/post")
    public ResponseEntity<?> postMemo(@RequestBody MemoDto memoDto, Authentication authentication) {
        Long signedUserId = getUserIdFromAuthentication(authentication);
        Long memoId = memoService.addMemo(memoDto, signedUserId);

        Map<String, Object> response = new HashMap<>();
        response.put("code", "200 성공");
        response.put("memo_id", memoId);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "유저 여행 메모 수정")
    @PatchMapping("/upd")
    public ResponseEntity<?> updateMemo(@RequestBody MemoDto memoDto, Authentication authentication) {
        Long signedUserId = getUserIdFromAuthentication(authentication);
        memoService.updateMemo(memoDto, signedUserId);

        Map<String, Object> response = new HashMap<>();
        response.put("code", "200 성공");

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "유저 여행 메모 삭제")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMemo(@RequestParam Long memoId, Authentication authentication) {
        Long signedUserId = getUserIdFromAuthentication(authentication);
        memoService.deleteMemo(memoId, signedUserId);

        Map<String, Object> response = new HashMap<>();
        response.put("code", "200 성공");

        return ResponseEntity.ok(response);
    }

    private Long getUserIdFromAuthentication(Authentication authentication) {
        // Replace with your actual logic to extract signedUserId
        return (Long) authentication.getPrincipal();
    }






}



