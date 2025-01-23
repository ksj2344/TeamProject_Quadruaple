package com.green.project_quadruaple.wishlist.model.wishlistDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class WishListRes {
    private final long strfId;
    private final String category;
    private final String strfPic;
    private final String title;
    private final String locationTitle;
    private final int ratingCnt;
    private final double averageRating;
    private final int wishCnt;
}
