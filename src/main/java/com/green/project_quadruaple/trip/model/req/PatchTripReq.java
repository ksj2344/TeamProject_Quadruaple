package com.green.project_quadruaple.trip.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PatchTripReq {

    private String title;

    @JsonProperty("start_at")
    private String startAt;

    @JsonProperty("end_at")
    private String endAt;

    @JsonProperty("ins_user_list")
    private String insUserList;

    @JsonProperty("del_user_list")
    private String delUserList;

    @JsonProperty("ins_location_list")
    private String insLocationList;

    @JsonProperty("del_location_list")
    private String delLocationList;
}