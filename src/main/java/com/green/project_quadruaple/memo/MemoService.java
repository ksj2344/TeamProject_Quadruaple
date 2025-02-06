package com.green.project_quadruaple.memo;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.config.security.AuthenticationFacade;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.memo.model.ForbiddenException;
import com.green.project_quadruaple.memo.model.MemoDto;
import com.green.project_quadruaple.memo.model.Req.MemoPostReq;
import com.green.project_quadruaple.memo.model.Req.MemoUpReq;
import com.green.project_quadruaple.memo.model.Res.MemoGetRes;
import com.green.project_quadruaple.user.UserService;
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
    private final AuthenticationFacade authenticationFacade;

    @Transactional
    public  ResponseEntity<ResponseWrapper<MemoGetRes>> getMemo(Long memoId) {
        long signedUserId=authenticationFacade.getSignedUserId();
        if(signedUserId==0){ return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ResponseWrapper<>(ResponseCode.Forbidden.getCode(), null));}
        MemoGetRes memo = memoMapper.selectMemo(memoId);



        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),memo));
    }


    @Transactional
    public ResponseEntity<ResponseWrapper<Long>> addMemo(MemoPostReq memoDto) {
        long signedUserId=authenticationFacade.getSignedUserId();
        if(signedUserId==0){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ResponseWrapper<>(ResponseCode.Forbidden.getCode(), 0L));
        }
        memoMapper.postMemo(memoDto);
        return ResponseEntity.ok(
                new ResponseWrapper<>(ResponseCode.OK.getCode(), memoDto.getMemoId()));
    }



    // 메모 수정 권한
    @Transactional
    public ResponseEntity<ResponseWrapper<Integer>> updateMemo(MemoUpReq memoDto) {
        long signedUserId=authenticationFacade.getSignedUserId();
        if(signedUserId==0){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ResponseWrapper<>(ResponseCode.Forbidden.getCode(), null));
        }
        Long memoOwnerId = memoMapper.findMemoOwnerId(memoDto.getMemoId());

        if (!memoOwnerId.equals(signedUserId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponseWrapper<>(ResponseCode.Forbidden.getCode(), null));
        }

        memoMapper.patchMemo(memoDto);
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), 0));
    }

    // 메모 삭제 권한
    @Transactional
    public ResponseEntity<ResponseWrapper<Integer>> deleteMemo(Long memoId) {

        long signedUserId=authenticationFacade.getSignedUserId();
        if(signedUserId==0){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ResponseWrapper<>(ResponseCode.Forbidden.getCode(), null));
        }
        Long memoOwnerId = memoMapper.findMemoOwnerId(memoId);

        if (!memoOwnerId.equals(signedUserId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponseWrapper<>(ResponseCode.Forbidden.getCode(), null));
        }

        memoMapper.deleteMemo(memoId);
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), 0));
    }
}













