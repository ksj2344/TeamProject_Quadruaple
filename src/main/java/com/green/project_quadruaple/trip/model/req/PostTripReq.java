package com.green.project_quadruaple.trip.model.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PostTripReq {

    @JsonIgnore
    private long tripId;

    @JsonProperty("location_id")
    private List<Long> locationId;

    private String title;

    @JsonProperty("start_at")
    private String startAt;

    @JsonProperty("end_at")
    private String endAt;

    @JsonIgnore
    private long managerId;
}
