package com.green.project_quadruaple.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ReviewPostDto {
    @JsonIgnore
    private long reviewId;
    private String content;
    private int rating;
    private long userId;
    private long strfId;
    private List<MultipartFile> pics;
}
