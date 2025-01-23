package com.green.project_quadruaple.strf.model;


import java.util.List;

public class ReviewSelRes {
        private Long reviewId;
        private Long strfId;
        private Long userId;
        private int rating;
        private String content;
        private String writerUserName;
        private String writerUserPic;
        private List<String> pictures; // 리뷰 사진
        private String reviewWriteDate;

}
