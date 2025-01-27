package com.green.project_quadruaple.picmanager.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@EqualsAndHashCode
public class StrfPicReq {
    private String category;
    private long startId;
    private long endId;
}
