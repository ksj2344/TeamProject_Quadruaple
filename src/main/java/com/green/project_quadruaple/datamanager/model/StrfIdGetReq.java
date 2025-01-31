package com.green.project_quadruaple.datamanager.model;

import lombok.*;

@ToString
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StrfIdGetReq {
    private String category;
    private String strfTitle;
    private Long startId;
    private Long endId;
}
