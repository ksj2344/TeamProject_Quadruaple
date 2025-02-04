package com.green.project_quadruaple.tripreview;

import com.green.project_quadruaple.tripreview.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TripReviewMapper {
    //여행기 insert
    void insTripReview(TripReviewPostReq req);
    void insTripReviewPic(long tripReviewId, List<String> tripReviewPic);

    //여행기 select
    // 로그인한 사용자의 여행기 조회
    List<TripReviewGetDto> getMyTripReviews(long userId, String orderType);
    // 모든 사용자의 여행기 조회
    List<TripReviewGetDto> getAllTripReviews(String orderType);
    // 다른 사용자의 여행기 조회
    TripReviewGetDto getOtherTripReviewById(long tripReviewId);

    //여행기 update
    int updTripReview(TripReviewPatchDto dto);
    void delTripReviewPic(long tripReviewId);

    //여행기 delete
    int delTripReview(long tripReviewId);
    TripReviewDeleteDto selTripReviewByUserId(long tripReviewId);
    void delTripReviewLikeByTripReviewId(long tripReviewId);

    //여행기 추천
    int insTripLike(TripLikeDto like);
    int delTripLike(TripLikeDto like);
    Integer tripLikeCount(Long tripReviewId);

    //여행기 조회수
    int insTripReviewRecent(long userId, long tripReviewId);

    //여행기 스크랩
    int insTripReviewScrap(TripReviewScrapDto scrap);
}
