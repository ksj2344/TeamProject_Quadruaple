package com.green.project_quadruaple.datamanager.model;

import lombok.*;

import java.util.List;

@ToString
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StrfReviewGetReq {
    private String category;
    private String strfTitle;
    private String picFolder;
    private String content;
    private int rating;
    private long userId;
    private Long startId;
    private Long endId;
}
