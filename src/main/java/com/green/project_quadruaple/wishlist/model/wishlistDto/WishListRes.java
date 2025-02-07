package com.green.project_quadruaple.wishlist.model.wishlistDto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListRes {
    private long strfId;         // 찜 항목 ID
    private String category;     // 찜 항목 카테고리
    private String title;        // 찜 항목 제목
    private String locationTitle; // 위치 이름
    private double ratingCnt;    // 평가 점수
    private double averageRating; // 평균 점수
    private int reviewCnt;       // 리뷰 수
    private Integer wishCnt;    // 찜 개수
    private String strfPic;       // 상품 이미지
    private double ratingAvg;     // 평균 평점

    private boolean isMore;




}
