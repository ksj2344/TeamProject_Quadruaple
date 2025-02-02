package com.green.project_quadruaple.search.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Schema(title = "검색 정보")
@ToString
@EqualsAndHashCode
public class SearchBasicRes {
    private String searchTerm;

    private String type; // 'recent' 또는 'popular'
    private String inquiredAt;
    private Long popularCount;

    private boolean isMore;

}

