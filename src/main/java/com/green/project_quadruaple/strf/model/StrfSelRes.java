package com.green.project_quadruaple.strf.model;

import com.green.project_quadruaple.common.config.enumdata.StrfCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Schema
public class StrfSelRes {
    @Schema(description = "카테고리" , example = "STAY,TOUR,RESTAUR,FEST")
    private String category;
    @Schema(description = "상품 pk")
    private long strfId;
    @Schema(description = "상품 제목")
    private String strfTitle;
    @Schema(description = "상품의 위도")
    private double latit;
    @Schema(description = "상품의 경도")
    private double longitude;
    @Schema(description = "상품의 주소")
    private String address;
    @Schema(description = "상품의 우편번호")
    private String post;
    @Schema(description = "상품의 전화번호")
    private String tell;
    @Schema(description = "상품의 시작일")
    private String startAt;
    @Schema(description = "상품의 종료일")
    private String endAt;
    @Schema(description = "상품의 운영 시작")
    private String open;
    @Schema(description = "상품의 운영 종료")
    private String close;
    @Schema(description = "상품의 휴무일")
    private String restDate;
    @Schema(description = "상품 소개")
    private String explain;
    @Schema(description = "상품 상세 소개")
    private String detail;
    @Schema(description = "상품 지역명")
    private String locationName;
    @Schema(description = "상품 사업자 번호")
    private String busiNum;
    @Schema(description = "상품 편의")
    private String amenity;
    @Schema(description = "상품 메뉴 pk ")
    private String menuId;
    @Schema(description = "상품 메뉴 이름")
    private String menuTitle;
    @Schema(description = "상품 메뉴 사진")
    private String menuPic;
    @Schema(description = "상품의 찜 갯수")
    private int wishCnt;
    @Schema(description = "상품의 평점 평균")
    private int ratingAvg;
    @Schema(description = "상품의 사업자 이름")
    private String hostName;
    @Schema(description = "상품의 사업자 사진")
    private String hostProfilePic;

    @Schema(description = "유저의 상품 찜 여부")
    private boolean wishIn;
    @Schema(description = "상품의 평점 갯수")
    private int ratingCnt;


}
