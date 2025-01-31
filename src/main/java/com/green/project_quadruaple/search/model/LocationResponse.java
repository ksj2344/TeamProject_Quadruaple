package com.green.project_quadruaple.search.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocationResponse {
    private String code;
    private String locationTitle;
    private String locationPic;
    private Integer locationId;
}
