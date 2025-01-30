package com.green.project_quadruaple.strf.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Schema
public class StrfDto {
    private long strfId;
    private String category;
    private String strfTitle;
    private double latit;
    private double longitude;
    private String address;
    private String post;
    private String tell;
    private String startAt;
    private String endAt;
    private String openCheck;
    private String closeCheck;
    private String restDate;
    private String explain;
    private String detail;
    private String busiNum;
    private String locationName;
    private String hostName;
    private String hostProfilePic;
    private int wishCnt;
    private double ratingAvg;
    private int recentCheck;
    private String inquiredAt;
    private String recentCheckStatus;

    private int wishIn;

    private List<Amenity> amenities;
    private List<StrfPicSel> strfPics;
    private List<Menu> menu;
    private List<StrfSelRes> res;
}
