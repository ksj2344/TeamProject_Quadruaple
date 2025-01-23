package com.green.project_quadruaple.strf;

import com.green.project_quadruaple.strf.model.StrfDto;
import com.green.project_quadruaple.strf.model.StrfSelReq;
import com.green.project_quadruaple.strf.model.StrfSelReviewReq;
import com.green.project_quadruaple.strf.model.StrfSelReviewRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("detail")
public class StrfController {
    private final StrfService service;

    @GetMapping
    public StrfDto getDetail(StrfSelReq req){


        return null;
    }

    @GetMapping("review")
    public List<StrfSelReviewRes> selReviewListWithCount (StrfSelReviewReq req){

        return null;
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

}
