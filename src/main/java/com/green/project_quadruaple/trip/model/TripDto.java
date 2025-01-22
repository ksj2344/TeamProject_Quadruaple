package com.green.project_quadruaple.trip.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TripDto {

    private long tripId;
    private String locationPic;
    private String title;
    private int scheduleCnt;
    private String startAt;
    private String endAt;

}
