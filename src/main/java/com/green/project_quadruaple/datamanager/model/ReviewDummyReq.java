package com.green.project_quadruaple.datamanager.model;

import com.green.project_quadruaple.review.model.ReviewPostReq;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDummyReq {
    private Long strfId;
    private String content;
    private int rating;
    private int num; // 몇 개의 리뷰를 생성할 것인지
}
