package com.green.project_quadruaple.strf;

import com.green.project_quadruaple.strf.model.StrfSelReq;
import com.green.project_quadruaple.strf.model.StrfSelRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StrfMapper {
    List<StrfSelRes> getDetail(StrfSelReq req);
//    getReview;
//    postBooking;
}
