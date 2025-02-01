package com.green.project_quadruaple.search.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
public class SearchPageRes {
    private long locationId; // 명세서에서 위치 ID
    private List<String> recentList; // 최근 검색어 목록
    private List<String> hotKeyWords; // 인기 검색어 목록
    private String locationTitle; // 위치 제목
    private String searchTxt;     // 검색된 텍스트
    private String category;      // 카테고리
    private long reviewCount;     // 리뷰 수
    private double averageRating; // 평균 평점s
    private String code;         // 응답 코드
    private String locationPic;    // 지역 사진





    @Getter
    @AllArgsConstructor
    @ToString
    public static class RecentItem {
        private final long strfId;
        private final String title; // 검색된 위치 제목
        private final String category;
    }
}