package com.green.project_quadruaple.review;

import com.green.project_quadruaple.review.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<ReviewSelRes> getReview(ReviewSelReq req);

    int postRating(ReviewPostReq p);
    int postReviewPic(ReviewPicDto p);

    int patchReview(ReviewUpdReq req);
    int patchReviewPic(ReviewPicDto p);
    List<String> getReviewPics(@Param("reviewId") long reviewId);

    int deleteReview (ReviewDelReq req);
    int deleteReviewPic(ReviewDelReq p);




}
