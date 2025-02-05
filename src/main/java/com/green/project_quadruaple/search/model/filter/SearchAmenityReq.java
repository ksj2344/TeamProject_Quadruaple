package com.green.project_quadruaple.search.model.filter;

import com.green.project_quadruaple.common.model.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SearchAmenityReq extends Paging {
    @Schema(title = "편의시설 ID 목록", example = "[35, 36]")
    private List<Long> amenityId = new ArrayList<>();

    @Schema(title = "검색어", example = "부산")
    private String searchWord;

    public SearchAmenityReq(Integer page, Integer size) {
        super(page, size);
    }

    public SearchAmenityReq(Integer page, Integer size, List<Long> amenityId, String searchWord) {
        super(page, size);
        this.amenityId = amenityId != null ? amenityId : new ArrayList<>();
        this.searchWord = searchWord;
    }
}

