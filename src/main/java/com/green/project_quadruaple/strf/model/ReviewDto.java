package com.green.project_quadruaple.strf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ReviewDto {
    private Long reviewId;
    private Long strfId;
    private Long userId;
    private int rating;
    private String content;
    private String writerUserName;
    private String writerUserPic;
    private List<String> pictures; // 리뷰 사진
    private String reviewWriteDate;
}
