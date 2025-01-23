package com.green.project_quadruaple.strf.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Schema
public class StrfPostBooking {
    private long strfId;
    private String message;
    private long scheduleId;
    private String visitAt;
    private long actualPaid;
    private List<OrderList> orderLists;
}
