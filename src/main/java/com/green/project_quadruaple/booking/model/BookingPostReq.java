package com.green.project_quadruaple.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BookingPostReq {

    @JsonIgnore
    private long bookingId;
    private String checkIn;
    private String checkOut;
    private int finalPayment;

}
