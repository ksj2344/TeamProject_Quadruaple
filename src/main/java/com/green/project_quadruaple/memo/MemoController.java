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
    @GetMapping("/select")
    public ResponseEntity<?> selectMemo(@RequestParam Long memoId, Authentication authentication) {
        Long signedUserId = (Long) authentication.getPrincipal();
        MemoDto memo = memoService.getMemo(memoId, signedUserId);

        Map<String, Object> response = new HashMap<>();
        response.put("code", "200 성공");
        response.put("content", memo.getContent());
        return ResponseEntity.ok(response);
    }
    @PostMapping("/post")
    public ResponseEntity<?> postMemo(@RequestBody MemoDto memoDto, Authentication authentication) {
        Long signedUserId = (Long) authentication.getPrincipal();
        Long memoId = memoService.addMemo(memoDto, signedUserId);
        Map<String, Object> response = new HashMap<>();
        response.put("code", "200 성공");
        response.put("tripId", memoDto.getTripId());
        response.put("day", memoDto.getDay());
        response.put("seq", memoDto.getSeq());
        response.put("content", memoDto.getContent());
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/upd")
    public ResponseEntity<?> updateMemo(@RequestBody MemoDto memoDto, Authentication authentication) {
        Long signedUserId = (Long) authentication.getPrincipal();
        memoService.updateMemo(memoDto, signedUserId);
        Map<String, Object> response = new HashMap<>();
        response.put("code", "200 성공");
        response.put("memoId", memoDto.getMemoId());
        response.put("tripId", memoDto.getTripId());
        response.put("content", memoDto.getContent());
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMemo(@RequestParam Long memoId, Authentication authentication) {
        Long signedUserId = (Long) authentication.getPrincipal();
        memoService.deleteMemo(memoId, signedUserId);
        Map<String, Object> response = new HashMap<>();
        response.put("code", "200 성공");
        response.put("memoId", memoId);
        return ResponseEntity.ok(response);
    }






}



