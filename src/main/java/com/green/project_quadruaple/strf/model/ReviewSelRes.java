package com.green.project_quadruaple.strf.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class ReviewSelRes {
        @Schema(description = "리뷰 목록")
        private List<ReviewDto> reviewList;

        @Schema(description = "작성자 ID", example = "12345")
        private long userId;

        @Schema(description = "평점", example = "5")
        private int rating;

        @Schema(description = "리뷰 내용")
        private String content;

        @Schema(description = "리뷰 작성 날짜")
        private String reviewWriteDate;

        @Schema(description = "리뷰 개수")
        private double reviewCount;

        @Schema(description = "작성자 이름")
        private String writerUserName;

        @Schema(description = "리뷰 ID")
        private long reviewId;

        @Schema(description = "작성자 프로필 사진")
        private String writerUserPic;

        @Schema(description = "리뷰 사진 리스트")
        private List<String> pictures;

        @Schema(description = "더 보기 리뷰 여부")
        private boolean moreReview;
}
