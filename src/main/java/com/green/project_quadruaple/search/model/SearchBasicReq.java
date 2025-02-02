package com.green.project_quadruaple.search.model;

import com.green.project_quadruaple.common.model.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Schema(title = "검색 기본 페이지")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SearchBasicReq extends Paging {
    private long userId;

    public SearchBasicReq(Integer page, Integer size , long userId) {
        super(page, size);
        this.userId = userId;
    }
}
