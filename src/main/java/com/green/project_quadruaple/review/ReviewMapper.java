package com.green.project_quadruaple.review;

import com.green.project_quadruaple.review.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<ReviewSelRes> getReviewWithPics(ReviewSelReq req);
    List<MyReviewSelRes> getMyReviews(MyReviewSelReq req);

    int postRating(ReviewPostReq p);
    int postReviewPic(ReviewPicDto pics);

    int patchReview(ReviewUpdReq req);
    int patchReviewPic(@Param("pics") List<ReviewPicDto> reviewPicList);

    int deleteReview (ReviewDelReq req);
    int deleteReviewPic(ReviewDelPicReq p);


}
