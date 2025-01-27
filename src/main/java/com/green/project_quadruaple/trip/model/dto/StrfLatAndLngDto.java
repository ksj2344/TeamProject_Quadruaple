package com.green.project_quadruaple.trip.model.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StrfLatAndLngDto {

    private Double Lat;
    private Double Lng;
}
