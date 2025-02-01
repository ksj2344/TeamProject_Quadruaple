package com.green.project_quadruaple.search;


import com.green.project_quadruaple.search.model.LocationResponse;
import com.green.project_quadruaple.trip.model.dto.LocationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
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
}


