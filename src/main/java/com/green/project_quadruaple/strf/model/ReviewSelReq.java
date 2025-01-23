package com.green.project_quadruaple.strf.model;

import com.green.project_quadruaple.common.model.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;
@Getter
@ToString
@Schema(title = "리뷰 요청")
public class ReviewSelReq {
    private static final int FIRST_COMMENT_SIZE = 3;

    @Positive
    @Schema(title = "상품 PK", name = "strf_id", description = "상품 PK", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long strfId;

    @PositiveOrZero
    @Schema(title = "리뷰 조회 시작 인덱스", name = "start_idx", example = "0", requiredMode = Schema.RequiredMode.REQUIRED)
    private int startIdx;

    @Min(value = 20, message = "페이지 크기는 최소 20이어야 합니다.")
    @Schema(title = "페이지 크기", description = "한 페이지에 표시할 리뷰 수", example = "20")
    private int size;

    public ReviewSelReq(long strfId, int startIdx, Integer size) {
        this.strfId = strfId;
        this.startIdx = startIdx;
        this.size = (size == null ? 20 : size) + 1;
    }
}

