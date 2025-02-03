package com.green.project_quadruaple.search.model;


import com.green.project_quadruaple.common.model.Paging;
import com.green.project_quadruaple.search.model.strf_list.SearchBaseList;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode
@Schema(title = "검색 조회 시 전체 출력 부분")
public class SearchAllList{
    private long strfId;
    private String strfTitle;
    private String category;
    private String locationName;
    private int aveargeRating;
    private int reviewCount;
    private int wishlistCount;
    private int userWishlist;
    private String amenities;

}
