package com.green.project_quadruaple.expense.model.req;


import com.green.project_quadruaple.expense.model.dto.DeDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString(callSuper = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ExpenseSameReq extends DeDto {
    @Schema(title = "인당가격",description = "총액/userIds의 길이", example = "3200")
    private int price;
    @Schema(title = "여행PK",example = "1")
    private long tripId;
    @Schema(title = "결제인원",example = "[101,104,108,109]")
    private List<Long> userIds;
}

