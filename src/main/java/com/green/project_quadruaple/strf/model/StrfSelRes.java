package com.green.project_quadruaple.strf.model;

import com.green.project_quadruaple.common.config.enumdata.StrfCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Schema
public class StrfSelRes {
    @Schema(description = "유저 id ")
    private Long userId;
    @Schema(description = "카테고리", example = "STAY, TOUR, RESTAUR, FEST")
    private String category;
    @Schema(description = "상품 ID", example = "12345")
    private long strfId;
    @Schema(description = "상품 제목")
    private String strfTitle;
    @Schema(description = "위도")
    private double latit;
    @Schema(description = "경도")
    private double longitude;
    @Schema(description = "주소")
    private String address;
    @Schema(description = "우편번호")
    private String post;
    @Schema(description = "전화번호")
    private String tell;
    @Schema(description = "시작일")
    private String startAt;
    @Schema(description = "종료일")
    private String endAt;
    @Schema(description = "운영 시작 시간")
    private String openCheck;
    @Schema(description = "운영 종료 시간")
    private String closeCheck;
    @Schema(description = "휴무일")
    private String restDate;
    @Schema(description = "상품 설명")
    private String explain;
    @Schema(description = "상세 설명")
    private String detail;
    @Schema(description = "지역 이름")
    private String locationName;
    @Schema(description = "사업자 번호")
    private String busiNum;
    @Schema(description = "편의시설")
    private String amenityId;
    @Schema(description = "편의이름")
    private String amenityTitles;
    @Schema(description = "사업자 이름")
    private String hostName;
    @Schema(description = "사업자 사진")
    private String hostProfilePic;
    @Schema(description = "찜 개수")
    private int wishCnt;
    @Schema(description = "평점 평균")
    private int ratingAvg;
    @Schema(description = "찜 여부")
    private int wishIn;
    @Schema(description = "평점 개수")
    private int ratingCnt;
    @Schema(description = "최근 본 목록 비활성화")
    private int recentCheck;
    @Schema(description = "최근 본 목록에서 상품 조회 시간")
    private String inquiredAt;
    private String recentCheckStatus;
}
