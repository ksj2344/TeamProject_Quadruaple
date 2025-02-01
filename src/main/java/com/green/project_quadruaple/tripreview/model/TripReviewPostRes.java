package com.green.project_quadruaple.tripreview.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TripReviewPostRes {
    private long tripReviewId;
    private List<String> tripReviewPic;
}
