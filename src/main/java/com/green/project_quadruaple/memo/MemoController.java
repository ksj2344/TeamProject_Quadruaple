package com.green.project_quadruaple.memo;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.memo.model.MemoDto;
import com.green.project_quadruaple.memo.model.Req.MemoPostReq;
import com.green.project_quadruaple.memo.model.Req.MemoUpReq;
import com.green.project_quadruaple.memo.model.Res.MemoGetRes;
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
    public ResponseEntity<?> selectMemo(@RequestParam Long memoId) {
        return memoService.getMemo(memoId);
    }

    @PostMapping("/post")
    public ResponseEntity<?> postMemo(@RequestBody MemoPostReq memoDto) {

        Map<String, Object> response = new HashMap<>();
        response.put("code", "200 성공");
        response.put("tripId", memoDto.getTripId());
        response.put("day", memoDto.getDay());
        response.put("seq", memoDto.getSeq());
        response.put("content", memoDto.getContent());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/upd")
    public ResponseEntity<?> updateMemo(@RequestBody MemoUpReq memoDto) {
        memoService.updateMemo(memoDto);
        Map<String, Object> response = new HashMap<>();
        response.put("code", "200 성공");
        response.put("memoId", memoDto.getMemoId());
        response.put("tripId", memoDto.getTripId());
        response.put("content", memoDto.getContent());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMemo(@RequestParam Long memoId) {
        memoService.deleteMemo(memoId);
        Map<String, Object> response = new HashMap<>();
        response.put("code", "200 성공");
        response.put("memoId", memoId);
        return ResponseEntity.ok(response);
    }






}



