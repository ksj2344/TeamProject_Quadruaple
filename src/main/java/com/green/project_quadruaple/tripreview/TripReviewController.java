package com.green.project_quadruaple.tripreview;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.tripreview.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("trip-review")
@Tag(name = "여행기")
public class TripReviewController {
    private final TripReviewService tripReviewService;

    //여행기 등록
    @PostMapping
    @Operation(summary = "여행기 등록")
    public ResponseEntity<?> postTripReview(@RequestPart(required = false) List<MultipartFile> tripReviewPic, @RequestPart TripReviewPostReq req){
        TripReviewPostRes res = tripReviewService.postTripReview(tripReviewPic, req);
        if (res == null) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ResponseWrapper<>(ResponseCode.BAD_GATEWAY.getCode(), null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), res));
    }

    //여행기 조회
    @GetMapping("myTripReview")
    @Operation(summary = "내 여행기 조회")
    public ResponseEntity<?> getMyTripReview(@Parameter(name = "orderType", description = "정렬 방식 (latest: 최신순, popular: 추천순)", example = "latest", in = ParameterIn.QUERY)
                                                 @RequestParam(defaultValue = "latest") String orderType) {
        List<TripReviewGetDto> myTripReview = tripReviewService.getMyTripReviews(orderType);

        if (myTripReview == null || myTripReview.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), myTripReview));
    }

    @GetMapping("allTripReview")
    @Operation(summary = "모든 여행기 조회")
    public ResponseEntity<?> getAllTripReview(@Parameter(name = "orderType", description = "정렬 방식 (latest: 최신순, popular: 추천순)", example = "latest", in = ParameterIn.QUERY)
                                                  @RequestParam(defaultValue = "latest") String orderType) {
        List<TripReviewGetDto> allTripReview = tripReviewService.getAllTripReviews(orderType);

        if (allTripReview == null || allTripReview.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), allTripReview));
    }

    @GetMapping("otherTripReview")
    @Operation(summary = "다른 사용자의 여행기 조회")
    public ResponseEntity<?> getOtherTripReview(@RequestParam long tripReviewId) {
        TripReviewGetDto tripReviewGetDto = tripReviewService.getOtherTripReviews(tripReviewId);

        if (tripReviewGetDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), tripReviewGetDto));
    }

    //여행기 수정
    @PatchMapping
    @Operation(summary = "여행기 수정")
    public ResponseEntity<?> patchTripReview(@RequestPart(required = false) List<MultipartFile> tripReviewPic, @RequestPart TripReviewPatchDto dto) {
        int result = tripReviewService.patchTripReview(tripReviewPic, dto);

        if (result == 0) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), result));
    }

    //여행기 삭제
    @DeleteMapping
    @Operation(summary = "여행기 삭제")
    public ResponseEntity<?> deleteTripReview(@RequestParam long tripReviewId) {
        int result = tripReviewService.deleteTripReview(tripReviewId);

        if (result == 0) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ResponseWrapper<>(ResponseCode.BAD_GATEWAY.getCode(),null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), result));
    }

    //여행기 추천
    @PostMapping("like")
    @Operation(summary = "여행기 추천 등록")
    public ResponseEntity<?> insTripLike(@RequestBody TripLikeDto like) {
        int result = tripReviewService.insTripLike(like);
        if (result == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), result));
    }

    @DeleteMapping("like")
    @Operation(summary = "여행기 추천 취소")
    public ResponseEntity<?> delTripLike(@RequestBody TripLikeDto like) {
        int result = tripReviewService.delTripLike(like);
        if (result == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), result));
    }

    @GetMapping("like/count")
    @Operation(summary = "여행기 추천 수")
    public ResponseEntity<?> countLike(@RequestParam Long tripReviewId) {
        int result = tripReviewService.getTripLikeCount(tripReviewId);
        if (result == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(), result));
    }
}
