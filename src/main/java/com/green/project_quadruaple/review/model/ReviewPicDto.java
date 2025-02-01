package com.green.project_quadruaple.review.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class ReviewPicDto {
    private long reviewId;
    private String pic;
}
