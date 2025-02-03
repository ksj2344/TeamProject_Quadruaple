package com.green.project_quadruaple.review;

import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.review.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("review")
@Tag(name = "리뷰")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    @Operation(summary = "리뷰 조회")
    public ResponseEntity<?> getReview(@Valid @ModelAttribute ReviewSelReq req) {
        ResponseEntity<ResponseWrapper<List<ReviewSelRes>>> response = reviewService.getReview(req);

        return ResponseEntity.ok(response.getBody());
    }
    @GetMapping("my")
    @Operation(summary = "나의 리뷰 조회")
    public ResponseEntity<?> getReview(@Valid @ModelAttribute MyReviewSelReq req) {
        ResponseWrapper<List<MyReviewSelRes>> response = reviewService.getMyReviews(req);

        return ResponseEntity.ok(response); // ResponseWrapper를 직접 반환
    }

//    @PostMapping
//    public ResponseEntity<?> postReview(@RequestBody ReviewPostReq reviewPostReq,
//                                           @RequestParam List<ReviewPostPic> pics) {
//        ResponseWrapper<Long> reviewId = reviewService.postReview(reviewPostReq, pics);
//        return ResponseEntity.ok(reviewId);
//    }

//    @PutMapping
//    @Operation(summary = "리뷰 수정")
//    public ResponseEntity<?> updateReview(@RequestPart List<MultipartFile> pics,
//                                                                 @Valid @RequestPart ReviewUpdReq p) {
//
//        ResponseEntity<ResponseWrapper<Integer>> response = reviewService.updateReview(pics, p);
//        return response;
//    }


    @DeleteMapping("/{reviewId}")
    @Operation(summary = "리뷰 삭제")
    public ResponseEntity<ResponseWrapper<Integer>> deleteReview(
            @PathVariable long reviewId,
            @ParameterObject @ModelAttribute ReviewDelReq req) {
        req.setReviewId(reviewId); // URL에서 리뷰 ID를 설정
        return reviewService.deleteReview(req);
    }





}
