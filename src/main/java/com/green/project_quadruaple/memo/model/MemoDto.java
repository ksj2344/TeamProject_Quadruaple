package com.green.project_quadruaple.memo.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MemoDto {
    private Long scheduleMemoId;
    private Long memoId;
    private Long tripId;
    private Integer day;
    private Integer seq;
    private Long userId;
    private String title;
    private String content;
    private String category;
}


