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
public class StrfDetailDto {
    private String category;
    private long strfId;
    private String strfTitle;
    private double latit;
    private double longitude;
    private String address;
    private String post;
    private String tell;
    private String startAt;
    private String endAt;
    private String open;
    private String close;
    private String restDate;
    private String explain;
    private String detail;
    private String locationName;
    private String busiNum;
    private String amenity;
    private String menuId;
    private String menuTitle;
    private String menuPic;
    private int wishCnt;
    private int ratingAvg;
    private String hostName;
    private String hostProfilePic;

    private boolean wishIn;
    private int ratingCnt;

    private List<StrfSelRes> res; // 연결할 리스트
}

