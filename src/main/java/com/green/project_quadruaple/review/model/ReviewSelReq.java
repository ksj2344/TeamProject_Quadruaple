package com.green.project_quadruaple.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.green.project_quadruaple.common.model.Paging;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.BindParam;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(title = "리뷰 요청")
public class ReviewSelReq extends Paging {
//    @JsonIgnore
//    private Long userId;

    @NotNull
    @Positive
    @Schema(title = "상품 PK",description = "상품 PK", example = "1")
    private Long strfId;

    public ReviewSelReq(Integer page, Integer size , Long strfId) {
        super(page, size);
        this.strfId = strfId;
    }
//    public void setUserId(Long userId){this.userId = userId;}
}
