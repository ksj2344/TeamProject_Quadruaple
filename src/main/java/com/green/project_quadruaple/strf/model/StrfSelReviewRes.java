package com.green.project_quadruaple.strf.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Schema
public class StrfSelReviewRes {
    private List<ReviewList> reviewLists;

    private long userId;
    private int rating;
    private String content;
    private String reviewWriteDate;
    private double reviewCount;
    private String writerUserName;
    private long reviewId;
    private String writerUserPic;
    private List<String> pictures;
}
