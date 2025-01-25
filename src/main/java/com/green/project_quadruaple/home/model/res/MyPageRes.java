package com.green.project_quadruaple.home.model.res;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MyPageRes {
    private int couponCnt;
    private String name;
    private String profilePic;
    private List<UserTripDto> tripList;
}
