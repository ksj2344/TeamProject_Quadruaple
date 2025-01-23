package com.green.project_quadruaple.strf;

import com.green.project_quadruaple.strf.model.StrfDto;
import com.green.project_quadruaple.strf.model.StrfSelReq;
import com.green.project_quadruaple.strf.model.StrfSelReviewReq;
import com.green.project_quadruaple.strf.model.StrfSelReviewRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StrfMapper {
    StrfDto getDetail(StrfSelReq req);
    StrfSelReviewReq getReview (StrfSelReviewReq req);
    List<StrfSelReviewRes> selReviewListWithCount (StrfSelReviewReq req);
//    postBooking;
}
