package com.green.project_quadruaple.strf;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.strf.model.StrfDto;
import com.green.project_quadruaple.strf.model.StrfSelReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("detail")
@RequiredArgsConstructor
@Tag(name = "상품")
public class StrfController {
    private final StrfService strfService;

    @GetMapping
    @Operation(summary = "상품 조회")
    public ResponseEntity<?> getDetail(@RequestParam(value = "user_id" ,required = false) Long userId,
                                       @RequestParam("strf_id") Long strfId) {

        if (strfId == null) {
            return ResponseEntity.badRequest().body(new ResponseWrapper<>(ResponseCode.BAD_REQUEST.getCode(), null));
        }

//        if (userId == null) {
//            // userId가 제공된 경우의 처리
//            // 예: userId를 사용하여 특정 로직 수행
//        } else {
//            // userId가 제공되지 않은 경우의 처리
//            // 예: 기본값 사용 또는 다른 로직 수행
//        }

        ResponseWrapper<StrfDto> detail = strfService.getDetail(userId,strfId);
        System.out.println("Current user_id4: " + userId);


        return ResponseEntity.ok(detail);
    }


}


//    @GetMapping("detail/review")
//    @PostMapping("booking-post")

//    @GetMapping("booking")
//    @GetMapping("booking-list")

//    @Operation(summary = "리뷰 목록 조회", description = "상품 PK를 기반으로 리뷰를 최신순 정렬로 가져옵니다.")
//    @GetMapping("/{strfId}")
//    public ResponseEntity<ReviewResponse> getReviews(
//            @Parameter(description = "상품 PK", example = "1", required = true)
//            @PathVariable Long strfId,
//            @Parameter(description = "최대 리뷰 개수", example = "4", required = false)
//            @RequestParam(defaultValue = "4") int limit
//    ) {
//        ReviewResponse response = reviewService.getReviews(strfId, limit);
//        return ResponseEntity.ok(response);
//    }