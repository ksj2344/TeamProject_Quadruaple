package com.green.project_quadruaple.search.model.filter;

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
public class SearchAmenityReq{
    @Schema(title = "편의시설 ID 목록", example = "[35, 36]")
    private List<Long> amenityId;

    @Schema(title = "검색어", example = "부산")
    private String searchWord;

}

