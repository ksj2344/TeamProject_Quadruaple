package com.green.project_quadruaple.review;

import com.green.project_quadruaple.review.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {
    List<ReviewSelRes> getReviewWithPics(ReviewSelReq req);
    List<MyReviewSelRes> getMyReviews(Long userId);

    int postRating(@Param("req") ReviewPostReq req, @Param("userId") Long userId);

    int postReviewPic(ReviewPicDto pics);

    int patchReview(ReviewUpdReq req);
    int patchReviewPic(@Param("pics") List<ReviewPicDto> reviewPicList);

    int deleteReview (Long reviewId,Long userId);
    int deleteReviewPic(Long reviewId);


}
