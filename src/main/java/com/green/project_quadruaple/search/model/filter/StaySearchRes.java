package com.green.project_quadruaple.search.model.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class StaySearchRes {
    private List<Stay> stays;
    private int totalCount;
}
