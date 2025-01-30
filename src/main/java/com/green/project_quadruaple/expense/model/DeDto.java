package com.green.project_quadruaple.expense.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DeDto {
    @JsonIgnore
    private Long deId;
    @Schema(title = "사용처",description = "어디에 사용했는지", example = "컴퍼스 커피")
    private String forText;
}
