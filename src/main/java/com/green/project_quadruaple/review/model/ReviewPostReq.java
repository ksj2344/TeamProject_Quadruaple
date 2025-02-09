package com.green.project_quadruaple.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ReviewPostReq {
    @JsonIgnore
    private Long reviewId;
    private Long strfId;
    private String content;
    private int rating;
}
