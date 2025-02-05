package com.green.project_quadruaple.datamanager.model;

import com.green.project_quadruaple.review.model.ReviewPostReq;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper = true)
public class ReviewDummyReq extends ReviewPostReq {
    private int num;
}
