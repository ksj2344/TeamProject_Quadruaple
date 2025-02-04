package com.green.project_quadruaple.search;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.config.enumdata.StrfCategory;
import com.green.project_quadruaple.common.model.Paging;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.search.model.*;
import com.green.project_quadruaple.search.model.filter.Stay;
import com.green.project_quadruaple.search.model.filter.StaySearchReq;
import com.green.project_quadruaple.search.model.filter.StaySearchRes;
import com.green.project_quadruaple.search.model.strf_list.GetSearchStrfListBasicRes;
import com.green.project_quadruaple.search.model.SearchBasicReq;
import com.green.project_quadruaple.trip.model.Category;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/search") // 겹치지 않도록 설정
@Tag(name = "검색")
public class SearchController {

    private final SearchService searchService;

    // 중복 생성자를 제거하고 아래처럼 정의
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    /*@GetMapping
    public ResponseEntity<Map<String, Object>> getTripLocation(
            @RequestParam(value = "search_word", required = false) String searchWord) {
        long userId = AuthenticationFacade.getSignedUserId(); // JWT에서 유저 ID 추출
        Map<String, Object> response = searchService.getTripLocation(userId, searchWord);
        return ResponseEntity.ok(response);
    }*/


    /*@GetMapping("/location")
    public ResponseEntity<?> getTripLocation(@RequestParam String search_word) {
        System.out.println("Received search_word: " + search_word);
        List<LocationResponse> locations = searchService.getTripLocation(search_word);
        System.out.println("Search results: " + locations);
        if (locations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("검색 결과가 없습니다.");
        }
        return ResponseEntity.ok(locations);
    }*/
    @GetMapping("/location")
    public ResponseEntity<?> getTripLocation(@RequestParam String search_word) {
        System.out.println("Received search_word: " + search_word); // 디버깅용 로그 추가
        List<LocationResponse> locations = searchService.getTripLocation(search_word);

        if (locations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("검색 결과가 없습니다.");
        }
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/strf-list-basic")
    @Operation(summary = "일정 추가 검색 기본", description = "일정 추가 검색 화면 처음 접근시 불러올 해당지역 추천 장소들 요청 API")
    public ResponseWrapper<GetSearchStrfListBasicRes> getStrfListBasic(@RequestParam("trip_id") Long tripId,
                                                                       @RequestParam("last_index") int lastIdx)
    {
        return searchService.getStrfListBasic(tripId, lastIdx);
    }

    @GetMapping("/strf-list-word")
    @Operation(summary = "일정 추가 검색", description = "일정 추가 검색, 검색어 입력 시 요청 API, 현재 필터는 카테고리와 검색어 뿐")
    public ResponseWrapper<GetSearchStrfListBasicRes> getStrfListWithSearchWord(@RequestParam("trip_id") Long tripId,
                                                                       @RequestParam("last_index") int lastIdx,
                                                                       @RequestParam(required = false) String category,
                                                                       @RequestParam(value = "search_word", required = false) String searchWord)
    {
        return searchService.getStrfListWithSearchWord(tripId, lastIdx, category, searchWord);
    }



    // 상품 검색어 최근 리스트

    @GetMapping("/list")
    @Operation(summary = "검색창 최근 검색어 리스트", description = "user_id 가 없으면 반환하는 데이터 없음")
    public ResponseWrapper<SearchGetRes> searchGetList (){
        return searchService.searchGetList();
    }


    // 밑으로 상품 검색

    @GetMapping("/basic")
    @Operation(summary = "상품 검색 최근 본 목록 리스트", description = "user_id 가 없으면 반환하는 데이터 없음")
    public ResponseWrapper<?> searchBasicList(@RequestParam(value = "user_id" , required = false) Long userId) {
        return searchService.searchBasicRecent(userId);
    }

    @GetMapping("/popular")
    @Operation(summary = "인기 상품", description = "현재 날짜 기준 3개월 동안 검색이 많은 상품")
    public ResponseWrapper<?> searchBasicPopular(){
        return searchService.searchBasicPopular();
    }

    @PostMapping("/all")
    @Operation(summary = "검색 - 전체 페이지 리스트", description = "최대 3개 출력 - 나머지는 더 보기 누르면 category 로 이전")
    public ResponseWrapper<?> searchAll(@RequestParam("search_word") String searchWord) {
        return searchService.searchAll(searchWord);
    }

    @GetMapping("/category")
    @Operation(summary = "카테고리 검색 ", description = "최대10개 출력 + 더 보기 누르면 계속 10개로 ")
    public List<Stay> searchCategoryWithFilters(@RequestParam("category") Category category
                                                , @RequestParam("start_idx") int startIdx
                                                , @RequestParam("size") int size
                                                , @RequestParam(value = "user_id",required = false) Long userId) {
        return searchService.searchCategoryWithFilters(category, startIdx, size, userId);
    }

    @GetMapping("/amenity")
    @Operation(summary = "숙소 카테고리 - 편의 필터", description = "편의 id -> 편의 정보에 맞춰 숙소 리스트 반환")
    public List<Stay> searchStayByAmenity(@RequestParam("amenity_id") Long amenityId
                                                , @RequestParam("start_idx") int startIdx
                                                , @RequestParam("size") int size
                                                , @RequestParam(value = "user_id" , required = false) Long userId) {
        return searchService.searchStayByAmenity(amenityId, startIdx, size, userId);
    }
}