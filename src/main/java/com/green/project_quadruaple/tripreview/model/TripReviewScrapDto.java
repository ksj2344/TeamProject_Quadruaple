package com.green.project_quadruaple.tripreview.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TripReviewScrapDto {
    @JsonIgnore
    private long scrapId;
    private long tripReviewId;
    private long tripId;
}
