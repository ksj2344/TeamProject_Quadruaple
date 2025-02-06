package com.green.project_quadruaple.search.model;

import com.green.project_quadruaple.common.model.Paging;
import com.green.project_quadruaple.search.model.strf_list.SearchBaseList;
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
public class SearchCategoryList extends SearchBaseList {
    public SearchCategoryList(Integer page, Integer size) {
        super(page, size);
    }
}
