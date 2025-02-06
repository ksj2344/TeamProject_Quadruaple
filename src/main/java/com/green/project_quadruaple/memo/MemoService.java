package com.green.project_quadruaple.memo;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.memo.model.ForbiddenException;
import com.green.project_quadruaple.memo.model.MemoDto;
import com.green.project_quadruaple.user.exception.CustomException;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoMapper memoMapper;

    @Transactional
    public MemoDto getMemo(Long memoId, Long signedUserId) {
        MemoDto memo = memoMapper.selectMemo(memoId);
        if (memo == null) {
            throw new ForbiddenException("조회 권한이 없습니다.");
        }
        if (!memo.getUserId().equals(signedUserId)) {
            throw new ForbiddenException("조회 권한이 없습니다.");
        }
        if (memo.getUpdatedAt() == null) {
            memo.setUpdatedAt(memo.getCreatedAt());
        }
        return memo;
    }

    @Transactional
    public Long addMemo(MemoDto memoDto, Long signedUserId) {
        memoDto.setUserId(signedUserId);
        memoDto.setCreatedAt(LocalDateTime.now());
        memoMapper.postMemo(memoDto);
        return memoDto.getMemoId();
    }

    @Transactional
    public void updateMemo(MemoDto memoDto, Long signedUserId) {
        Long memoOwnerId = memoMapper.findMemoOwnerId(memoDto.getMemoId());

        if (!memoOwnerId.equals(signedUserId)) {
            throw new ForbiddenException("403", "수정 권한이 없습니다.");
        }

        memoMapper.patchMemo(memoDto);
    }

    @Transactional
    public void deleteMemo(Long memoId, Long signedUserId) {
        Long memoOwnerId = memoMapper.findMemoOwnerId(memoId);

        if (!memoOwnerId.equals(signedUserId)) {
            throw new ForbiddenException("403", "삭제 권한이 없습니다.");
        }

        memoMapper.deleteMemo(memoId);
    }
}





    /*// 메모 삭제 로직 (day별 삭제)
    public ResponseEntity<ResponseWrapper<String>> deleteMemo(Long memoId, Integer day) {
        // Step 1. memo 테이블에서 관련된 메모 삭제
        int memoDeleted = memoMapper.deleteMemoById(memoId);
        if (memoDeleted == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), "삭제할 메모가 없습니다."));
        }

        // Step 2. sche_memo 테이블에서 day에 해당하는 데이터 삭제
        int scheMemoDeleted = memoMapper.deleteScheMemoByDay(memoId, day);
        if (scheMemoDeleted == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), "삭제할 스케줄 메모가 없습니다."));
        }

        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), "삭제 완료"));
    }*/








