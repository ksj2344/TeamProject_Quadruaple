package com.green.project_quadruaple.memo;

import com.green.project_quadruaple.memo.model.MemoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface MemoMapper {


    MemoDto selectMemo(Long memoId);

    void postMemo(MemoDto memoDto);

    Long findMemoOwnerId(Long memoId);

    void patchMemo(MemoDto memoDto);

    void deleteMemo(Long memoId);
}
