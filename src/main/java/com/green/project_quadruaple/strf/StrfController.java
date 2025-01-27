package com.green.project_quadruaple.strf;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.strf.model.StrfDto;
import com.green.project_quadruaple.strf.model.StrfSelReq;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("detail")
@RequiredArgsConstructor
public class StrfController {
    private final StrfService strfService;

    @GetMapping
    @Operation(summary = "상품 조회")
    public ResponseEntity<?> getDetail(@Valid @ModelAttribute StrfSelReq req) {
        // Service 호출하여 상세 정보 조회
        StrfDto detail = strfService.getDetail(req);

        // 데이터가 없을 경우 404 응답 반환
        if (detail == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));
        }

        // 정상적인 응답 반환
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),detail));
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