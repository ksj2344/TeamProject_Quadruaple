package com.green.project_quadruaple.search.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchPageReq {
    private long locationId; // 명세서에서 위치 ID
    private String category; // 카테고리 (TOUR, STAY 등)
    private String searchText; // 검색어
    private int page; // 페이지 번호
    private int pageSize; // 페이지 당 데이터 수
}