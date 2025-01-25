package com.green.project_quadruaple.strf.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Schema
public class StrfDto {
    private long strfId;      // 상품 ID
    private String category;  // 카테고리
    private String strfTitle; // 상품 제목
    private double latit;     // 위도
    private double longitude; // 경도
    private String address;   // 주소
    private String post;      // 우편번호
    private String tell;      // 전화번호
    private String startAt;   // 시작일
    private String endAt;     // 종료일
    private String open;      // 운영 시작 시간
    private String close;     // 운영 종료 시간
    private String restDate;  // 휴무일
    private String explain;   // 상품 설명
    private String detail;    // 상세 설명
    private String busiNum;  // 사업자 번호
    private String locationName; // 지역 이름
    private long amenityId;  // 편의시설
    private List<String> amenityTitles;
    private int menuPrice;
    private String menuId;   // 메뉴 ID
    private String menuTitle; // 메뉴 이름
    private String hostName; // 사업자 이름
    private String hostProfilePic; // 사업자 사진
    private int recentCheck; // 최근 본 목록 비활성화
    private String inquiredAt; // 최근 본 목록에서 상품의 조회 시간
    private int wishCnt;     // 찜 개수
    private int ratingAvg;   // 평점 평균
    private List<MenuDto> menuPics;  // 메뉴 사진


    private boolean wishIn;  // 찜 여부
    private int ratingCnt;   // 평점 개수

    private List<StrfSelRes> res; // StrfSelRes 객체 리스트

    public List<String> getAmenityTitles() {
        return amenityTitles;
    }

    public void setAmenityTitles(String amenityTitles) {
        this.amenityTitles = amenityTitles == null || amenityTitles.isEmpty()
                ? new ArrayList<>()
                : Arrays.asList(amenityTitles.split(", "));
    }
}
