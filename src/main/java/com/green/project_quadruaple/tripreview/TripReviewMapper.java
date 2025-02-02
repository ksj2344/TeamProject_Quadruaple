package com.green.project_quadruaple.tripreview;

import com.green.project_quadruaple.tripreview.model.TripLikeDto;
import com.green.project_quadruaple.tripreview.model.TripReviewPatchDto;
import com.green.project_quadruaple.tripreview.model.TripReviewPostReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TripReviewMapper {
    //여행기 insert
    void insTripReview(TripReviewPostReq req);
    void insTripReviewPic(long tripReviewId, List<String> tripReviewPic);

    //여행기 update
    int updTripReview(TripReviewPatchDto dto);
    void delTripReviewPic(long tripReviewId);

    //여행기 추천
    int insTripLike(TripLikeDto like);
    int delTripLike(TripLikeDto like);
    Integer tripLikeCount(Long tripReviewId);
}
