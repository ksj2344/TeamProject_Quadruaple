package com.green.project_quadruaple.memo;

import com.green.project_quadruaple.memo.model.MemoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemoService {



    private final MemoMapper memoMapper;
    private MemoMapper scheMemoMapper;


    @Transactional
    public Long insertMemoAndSchedule(MemoDto memoDto) {
        memoMapper.insertMemo(memoDto);
        Long memoId = memoDto.getMemoId();

        memoDto.setMemoId(memoId);
        memoMapper.insertScMemo(memoDto);

        return memoId;
    }


    public MemoDto selectMemo(Long memoId) {
        return memoMapper.selectMemo(memoId);
    }

    /*ublic Long insertMemo(MemoDto memoDto) {
        memoMapper.insertMemo(memoDto);
        memoMapper.insertScheMemo(memoDto);
        return memoDto.getScheduleMemoId();
    }*/
    public Long insertMemo(MemoDto memoDto) {
        memoMapper.insertMemo(memoDto); // INSERT 수행
        return memoDto.getMemoId();    // 자동 생성된 memo_id 반환
    }


    public void updateMemo(MemoDto memoDto) {
        memoMapper.updateMemo(memoDto);
    }

    // ok
    public void deleteMemo(Long memoId) {
        memoMapper.deleteMemo(memoId);
    }


}
