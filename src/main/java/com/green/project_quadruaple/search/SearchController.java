package com.green.project_quadruaple.search;


import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.search.model.LocationResponse;
import com.green.project_quadruaple.search.model.strf_list.GetSearchStrfListBasicRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping
public class SearchController {
    private final SearchService searchService;


    @Operation(summary = "여행 지역 검색", description = "입력된 지역 이름으로 여행 지역 정보를 검색합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LocationResponse.class))),
            @ApiResponse(responseCode = "404", description = "검색 결과 없음")
    })
    @GetMapping("/location")
    public ResponseEntity<LocationResponse> getTripLocation(
            @RequestParam(value = "search_word", required = true) String searchWord) {
        LocationResponse response = searchService.getLocation(searchWord);
        if ("404".equals(response.getCode())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }



    /*@GetMapping
    public ResponseEntity<Map<String, Object>> getTripLocation(
            @RequestParam(value = "search_word", required = false) String searchWord) {
        long userId = AuthenticationFacade.getSignedUserId(); // JWT에서 유저 ID 추출
        Map<String, Object> response = searchService.getTripLocation(userId, searchWord);
        return ResponseEntity.ok(response);
    }*/


    /*@GetMapping("/location")
    public ResponseEntity<Map<String, Object>> getTripLocation(
            @RequestParam(value = "search_word", required = true) String searchWord) {
        Map<String, Object> response = searchService.getTripLocation(searchWord);

        // HTTP 상태 코드 설정
        if (response.get("code").equals(ResponseCode.NOT_FOUND.getCode())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }*/

    @GetMapping("/search/strf-list-basic")
    public ResponseWrapper<GetSearchStrfListBasicRes> getStrfListBasic(@RequestParam("trip_id") Long tripId, @RequestParam("last_index") int lastIdx) {
        return searchService.getStrfListBasic(tripId, lastIdx);
    }

    @GetMapping("/search/strf-list-word")
    public ResponseWrapper<GetSearchStrfListBasicRes> getStrfListWithSearchWord(@RequestParam("trip_id") Long tripId,
                                                                       @RequestParam("last_index") int lastIdx,
                                                                       @RequestParam(required = false) String category,
                                                                       @RequestParam(value = "search_word", required = false) String searchWord)
    {
        return searchService.getStrfListWithSearchWord(tripId, lastIdx, category, searchWord);
    }
}