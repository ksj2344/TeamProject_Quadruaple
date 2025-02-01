package com.green.project_quadruaple.strf.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Schema
public class StrfSelReq {
    @NotNull
    @Schema(description = "상품 ID", example = "1")
    private long strfId;
    @JsonIgnore
    private long userId;
}

