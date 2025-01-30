package com.green.project_quadruaple.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResultResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingPostRes extends ResultResponse {
    public BookingPostRes() {
        super(ResponseCode.OK.getCode());
    }
    @JsonProperty("booking_id")
    private long bookingId;


    private String strfId;
    private String strfName;
    private String message;
}
