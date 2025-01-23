package com.green.project_quadruaple.trip.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    STAY("숙소"),
    TOUR("관광"),
    RESTAUR("맛집"),
    FEST("축제");

    private final String name;
}
