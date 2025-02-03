package com.green.project_quadruaple.memo;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.memo.model.MemoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @GetMapping("/select")
    public ResponseEntity<MemoDto> selectMemo(@RequestParam Long memoId) {
        MemoDto memo = memoService.selectMemo(memoId);
        return ResponseEntity.ok(memo);
    }

    /*@PostMapping("/post")
    public ResponseEntity<?> postMemo(@RequestBody MemoDto memoDto) {
        Long scheduleMemoId = memoService.insertMemo(memoDto);
        return ResponseEntity.ok(scheduleMemoId);
    }*/

    /*@PostMapping("/post")
    public ResponseEntity<Long> postMemo(@RequestBody MemoDto memoDto) {
        Long memoId = memoService.insertMemoAndScheMemo(memoDto);
        return ResponseEntity.ok(memoId);
    }*/

    @PostMapping("/post")
    public ResponseEntity<Long> postMemo(@RequestBody MemoDto memoDto) {
        // Memo와 관련된 데이터 삽입 처리
        Long memoId = memoService.insertMemoAndSchedule(memoDto);

        // 생성된 memoId 반환
        return ResponseEntity.ok(memoId);
    }

    @PatchMapping("/upd")
    public ResponseEntity<Map<String, String>> updateMemo(@RequestBody MemoDto memoDto) {
        memoService.updateMemo(memoDto);
        return ResponseEntity.ok(Map.of("code", "200 성공"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteMemo(@RequestParam Long memoId) {
        memoService.deleteMemo(memoId);
        return ResponseEntity.ok(Map.of("code", "200 성공"));
    }
}



