package com.green.project_quadruaple.strf;

import com.green.project_quadruaple.strf.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StrfMapper {
    // 상품 상세 정보를 가져오는 메서드
    StrfDto getDetail(StrfSelReq req);
    // 상품에 대한 리뷰 목록을 페이징 처리하여 가져오는 메서드
    List<ReviewSelRes> selReviewListWithCount(ReviewSelReq req); // 기존 메서드
    List<StrfPicSel> selReviewPicsByReviewIds(@Param("reviewIds") List<Long> reviewIds); // 새로운 메서드
    // 특정 리뷰 정보를 가져오는 메서드 (단일 리뷰 조회)
    ReviewSelRes getReview(ReviewSelReq req);
    // 특정 리뷰 ID 리스트에 해당하는 사진 정보를 가져오는 메서드
    List<StrfPicSel> selReviewPictures(List<Long> reviewIds);

    StrfDto getStrfDetail(StrfSelReq req);
    StrfDto strfDetailResultMap (StrfDto dto);

    // 예약 관련 메서드 (추후 구현 예정)
    // void postBooking(BookingReq req);
}

