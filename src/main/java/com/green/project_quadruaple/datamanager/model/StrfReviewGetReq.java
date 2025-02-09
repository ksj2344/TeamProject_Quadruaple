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
    private Long startId;
    private Long endId;
    private List<PicDto> pics;
}
