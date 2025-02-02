package com.green.project_quadruaple.recent.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class HideRecentUpdReq {
    private long userId;
    private long strfId;
}
