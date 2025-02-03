package com.green.project_quadruaple.search.model;

import com.green.project_quadruaple.common.model.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Schema(title = "검색 기본 페이지")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode
public class SearchBasicReq {
    private long userId;

}

