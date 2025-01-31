package com.green.project_quadruaple.review;

import com.green.project_quadruaple.review.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<ReviewSelRes> getReview(ReviewSelReq req);

    void postRating(ReviewPostDto dto);
    void postReviewPics(@Param("reviewId") long reviewId , @Param("pics") List<String> pics);

    int patchReview(ReviewUpdReq req);

    int deleteReview (ReviewDelReq req);
}
