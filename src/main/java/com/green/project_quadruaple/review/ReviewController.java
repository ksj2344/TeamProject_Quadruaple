package com.green.project_quadruaple.review;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.review.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
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
    public ResponseEntity<?> postRating(@RequestPart List<MultipartFile> pics
                                        ,@Valid @RequestPart ReviewPostReq p) {

        return reviewService.postRating(pics,p);
    }

    @PutMapping
    @Operation(summary = "리뷰 수정")
    public ResponseEntity<?> updateReview(@RequestPart List<MultipartFile> pics
            ,@Valid @RequestPart ReviewUpdReq p) {

        return reviewService.updateReview(pics,p);
    }

    @DeleteMapping
    @Operation(summary = "리뷰 삭제")
    public ResponseEntity<ResponseWrapper<Integer>> deleteReview (@ParameterObject @ModelAttribute ReviewDelReq req){

        return reviewService.deleteReview(req);
    }
}
