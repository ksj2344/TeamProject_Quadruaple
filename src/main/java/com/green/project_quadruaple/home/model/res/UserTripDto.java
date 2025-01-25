package com.green.project_quadruaple.home.model.res;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserTripDto {
    private long tripId;
    private String locationPic;
    private String title;
    private int dDay;
    //dDay가 -1이라면 여행중.

}
