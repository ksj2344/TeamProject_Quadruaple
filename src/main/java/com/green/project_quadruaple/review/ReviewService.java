package com.green.project_quadruaple.review;

import com.green.project_quadruaple.common.model.Constants;
import com.green.project_quadruaple.review.model.ReviewDto;
import com.green.project_quadruaple.review.model.ReviewSelReq;
import com.green.project_quadruaple.review.model.ReviewSelRes;
import com.green.project_quadruaple.strf.model.StrfPicSel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    @Autowired
    private ReviewMapper reviewMapper;
    public ReviewDto getReview(ReviewSelReq req) {
        ReviewDto dto = reviewMapper.getReview(req);
        dto.getStrfId();
//        if (dto == null) {
//            throw new IllegalArgumentException("Invalid Strf ID: " + req.getStrfId());
//        }
//        if (dto.getStrfId() != req.getStrfId()) {
//            throw new IllegalArgumentException("Invalid Strf ID: " + req.getStrfId());
//        }
        List<ReviewSelRes> reviewSelRes = dto.getRes();
        if (reviewSelRes == null) {
            throw new IllegalArgumentException("Invalid Strf ID: " + req.getStrfId());
        }
        dto.setRes(reviewSelRes);
        return dto;
    }

        /*
        // 리뷰 목록 가져오기 (페이징 처리된 목록)
        List<ReviewDto> reviewList = reviewMapper.getReview(req);
        if (reviewList.isEmpty()) {
            return new ArrayList<>();
        }

        // 리뷰 ID 리스트 가져오기
        List<Long> reviewIds = reviewList.stream()
                .map(ReviewDto::getReviewId)
                .collect(Collectors.toList());

        // 리뷰 사진 정보 가져오기
        List<StrfPicSel> strfPicSelList = reviewMapper.selReviewPicsByReviewIds(reviewIds);

        // 리뷰 ID별로 사진 목록 매핑
        Map<Long, List<String>> picMap = strfPicSelList.stream()
                .collect(Collectors.groupingBy(
                        StrfPicSel::getStrfId,
                        Collectors.mapping(StrfPicSel::getPic, Collectors.toList())
                ));

        // 리뷰 목록에 사진 추가
        for (ReviewDto review : reviewList) {
            review.setReviewPics(picMap.getOrDefault(review.getReviewId(), Collections.emptyList()));
        }

        // 리뷰 목록을 ReviewDto로 변환
        List<ReviewDto> reviewDtoList = reviewList.stream()
                .map(reviewSelRes -> {
                    ReviewDto dto = new ReviewDto();
                    dto.setReviewId(reviewSelRes.getReviewId());
                    dto.setContent(reviewSelRes.getContent());
                    dto.setRating(reviewSelRes.getRating());
                    dto.setWriterUserId(reviewSelRes.getWriterUserId());
                    dto.setWriterUserName(reviewSelRes.getWriterUserName());
                    dto.setWriterUserPic(reviewSelRes.getWriterUserPic());
                    dto.setUserWriteReviewCnt(reviewSelRes.getUserWriteReviewCnt());
                    dto.setReviewWriteDate(reviewSelRes.getReviewWriteDate());
                    dto.setReviewPics(reviewSelRes.getReviewPics());
                    return dto;
                })
                .collect(Collectors.toList());

        // 더보기 항목 추가
        boolean hasMore = reviewList.size() > Constants.getDefault_page_size();
        if (hasMore) {
            reviewDtoList = reviewDtoList.subList(0, Constants.getDefault_page_size());
            ReviewDto moreItem = new ReviewDto();
            moreItem.setMoreReview(true);
            reviewDtoList.add(moreItem);
        }

        return reviewDtoList;
    }

         */
}
