package com.green.project_quadruaple.trip.model.dto;

import com.green.project_quadruaple.trip.model.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScheduleDto {

    private int seq;
    private long distance;
    private int duration;
    private long strfId;
    private String strfTitle;
    private String category;
    private String address;
    private String lat;
    private String lng;
    private String pathType;
}

/*
* "schedules" : [
        {
          "seq" : 1,
          "strfId" : 1,
          "strfTitle" : "차이나 타운",
          "category" : "관광",
          "address" : "인천 중구",
          "lat" : 37.5664707,
          "lng" : 127.0039404,
          "distance" : 13920 or null,
          "duration" : 35 or null,
          "pathType" : "BUS" or null,
        },
        ...
      ]
* */