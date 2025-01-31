package com.green.project_quadruaple.review;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.review.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.attribute.UserPrincipal;
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
        List<ReviewSelRes> resList = reviewService.getReview(req);
        if (resList == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), resList));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), resList));
    }

    @PostMapping
    @Operation(summary = "리뷰 등록")
    public ResponseEntity<?> postRating(@RequestPart(value = "pics", required = false) List<MultipartFile> pics
                                        ,@Valid @ModelAttribute ReviewPostDto dto) {

        // 리뷰 DTO에 사진 추가
        dto.setPics(pics);

        // 서비스 호출
        reviewService.postRating(dto);

        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), dto));
    }

    @PutMapping
    public ResponseEntity<?> updateReview(@PathVariable long reviewId,
                                          @RequestBody ReviewUpdReq req,
                                          HttpServletRequest request) {

        long userId = (long) request.getAttribute("userId"); // 필터에서 저장된 userId 가져오기
        req.setReviewId(reviewId);
        req.setUserId(userId);

        ReviewUpdRes res = reviewService.updateReview(req).getData();
        return ResponseEntity.ok(res);
    }
}
