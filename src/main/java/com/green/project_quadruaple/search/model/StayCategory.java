package com.green.project_quadruaple.search.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class StayCategory {
    private Long strfId;
    private String strfTitle;
    private String category;
    private String locationName;
    private String strfPic;
    private Double averageRating;
    private Integer reviewCount;
    private Integer wishlistCount;
    private Integer userWishlist;
    private Long amenityId;
    private String amenityTitle;
}

