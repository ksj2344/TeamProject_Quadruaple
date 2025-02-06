package com.green.project_quadruaple.search.model.strf_list;

import com.green.project_quadruaple.common.model.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(title = "검색 기본 페이지")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SearchBaseList extends Paging {
    private Long strfId;
    private String title;
    private String category;
    private String locationName;
    private Double averageRating;
    private Integer reviewCount;
    private Integer wishlistCount;
    private Boolean userWishlist;
    private boolean isMore;

    public SearchBaseList(Integer page, Integer size) {
        super(page, size);
    }
}