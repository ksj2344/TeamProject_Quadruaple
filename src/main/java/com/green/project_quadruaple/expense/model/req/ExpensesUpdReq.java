package com.green.project_quadruaple.expense.model.req;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.green.project_quadruaple.expense.model.dto.UserPriceDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ExpensesUpdReq {
    @Schema(example = "1")
    private long deId;
    @Schema(example = "1")
    private long tripId;
    private List<UserPriceDto> priceList;
}
