package com.green.project_quadruaple.search;

import com.green.project_quadruaple.common.model.Paging;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.search.model.*;
import com.green.project_quadruaple.search.model.strf_list.GetSearchStrfListBasicRes;
import com.green.project_quadruaple.search.model.SearchBasicReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/search") // 겹치지 않도록 설정
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
    public ResponseWrapper<GetSearchStrfListBasicRes> getStrfListBasic(@RequestParam("trip_id") Long tripId, @RequestParam("last_index") int lastIdx) {
        return searchService.getStrfListBasic(tripId, lastIdx);
    }

    @GetMapping("/strf-list-word")
    public ResponseWrapper<GetSearchStrfListBasicRes> getStrfListWithSearchWord(@RequestParam("trip_id") Long tripId,
                                                                       @RequestParam("last_index") int lastIdx,
                                                                       @RequestParam(required = false) String category,
                                                                       @RequestParam(value = "search_word", required = false) String searchWord)
    {
        return searchService.getStrfListWithSearchWord(tripId, lastIdx, category, searchWord);
    }




    @PostMapping("/basic")
    public ResponseWrapper<?> searchBasicList(@RequestBody SearchBasicReq request) {
        return searchService.searchBasicList(request);
    }


    @GetMapping("/all")
    public ResponseWrapper<?> searchAllList(@RequestParam(value = "search_word") String searchWord, @RequestParam String category,
            @RequestParam(value = "user_id", required = false) Long userId, @ModelAttribute Paging paging) {

        return searchService.searchAllList(searchWord, category, userId, paging);
    }

    @GetMapping("/category")
    public ResponseWrapper<?> searchCategoryWithFilters(@RequestParam(value = "user_id", required = false) Long userId,
             @RequestParam String category, @RequestParam("amenity_id") List<Long> amenityIds, @ModelAttribute Paging paging) {

        return searchService.searchCategoryWithFilters(userId, category, amenityIds, paging);
    }





}