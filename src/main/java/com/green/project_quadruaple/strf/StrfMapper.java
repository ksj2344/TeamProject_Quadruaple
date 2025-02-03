package com.green.project_quadruaple.strf;

import com.green.project_quadruaple.review.model.ReviewSelReq;
import com.green.project_quadruaple.review.model.ReviewSelRes;
import com.green.project_quadruaple.strf.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface StrfMapper {
    StrfDto getDetail(@Param("userId") Long userId, @Param("strfId") Long strfId);
    void strfUpsert(@Param("userId") Long userId, @Param("strfId") Long strfId);
}

