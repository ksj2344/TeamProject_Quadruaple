package com.green.project_quadruaple.review.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class ReviewDto {
    private long strfId;
    private long reviewId;
    private String content;
    private Integer rating;
    private long writerUserId;
    private String writerUserName;
    private String writerUserPic;
    private Integer userWriteReviewCnt;
    private String reviewWriteDate;
    private List<String> reviewPics;
    private boolean moreReview;

    private List<ReviewSelRes> res;
}

