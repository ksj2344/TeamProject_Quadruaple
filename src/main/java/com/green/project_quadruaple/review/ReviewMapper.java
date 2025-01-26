package com.green.project_quadruaple.review;

import com.green.project_quadruaple.review.model.ReviewDto;
import com.green.project_quadruaple.review.model.ReviewSelReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    ReviewDto getReview(ReviewSelReq req);
}
