package com.green.project_quadruaple.tripreview.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TripReviewScrapDto {
    @JsonIgnore
    private long scrapId;
    private long tripId;
    private long tripReviewId;
    @JsonIgnore
    private long userId;
    private long scheMemoId;
    private long originalScheduleId;
    private int day;
    @Schema(title = "여행 시작 일자", type= "string", example = "2025-01-01", requiredMode = Schema.RequiredMode.REQUIRED)
    private String startAt;
    @Schema(title = "여행 완료 일자", type= "string", example = "2025-01-11", requiredMode = Schema.RequiredMode.REQUIRED)
    private String endAt;
}
