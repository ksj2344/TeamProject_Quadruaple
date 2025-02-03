package com.green.project_quadruaple.memo;

import com.green.project_quadruaple.memo.model.MemoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemoMapper {


    MemoDto selectMemo(@Param("memoId") Long memoId);

    void insertMemo(MemoDto memoDto);
    void insertScMemo(MemoDto memoDto);
    void updateMemo(MemoDto memoDto);

    void deleteMemo(@Param("memoId") Long memoId);
}
