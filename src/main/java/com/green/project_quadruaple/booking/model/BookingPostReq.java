package com.green.project_quadruaple.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.green.project_quadruaple.booking.model.dto.MenuIdAndQuantityDto;
import com.green.project_quadruaple.datamanager.model.MenuReq;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BookingPostReq {

    @NotNull
    @JsonProperty("strf_id")
    private long strfId;

    @NotBlank
    @JsonProperty("check_in")
    private String checkIn;

    @NotBlank
    @JsonProperty("check_out")
    private String checkOut;

    @JsonProperty("coupon_id")
    private long couponId;

    @NotNull
    @JsonProperty("actual_paid")
    private int actualPaid;

    @NotNull
    @JsonProperty("order_list")
    private List<MenuIdAndQuantityDto> orderList;

    @JsonIgnore
    private Long userId;

    @JsonIgnore
    private String tid;

    @JsonIgnore
    private Long bookingId;

    @JsonIgnore
    private Long receiveId;
}
/*
*    "strf_id" : 1,
   "visit_at" : "2025-01-16 15:03:00",
   "coupon_id" : 1
   "actual_paid" : 252220,
   "order_list" : [{"1":1},{"2":1}, ...]
* */