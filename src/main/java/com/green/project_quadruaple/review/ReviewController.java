package com.green.project_quadruaple.review;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.review.model.ReviewDto;
import com.green.project_quadruaple.review.model.ReviewSelReq;
import com.green.project_quadruaple.review.model.ReviewSelRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("review")
@Tag(name = "리뷰")
public class ReviewController {
    private ReviewService reviewService;
    @GetMapping
    @Operation(summary = "리뷰 조회")
    public ResponseEntity<?> getReview(@Valid @ModelAttribute ReviewSelReq req) {
        ReviewDto dto = reviewService.getReview(req);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), dto));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), dto));
    }
}
