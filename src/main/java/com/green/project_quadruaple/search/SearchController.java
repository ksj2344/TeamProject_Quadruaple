package com.green.project_quadruaple.search;


import com.green.project_quadruaple.search.model.SearchPageRes;
import com.green.project_quadruaple.search.model.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//
//
@RestController
@RequiredArgsConstructor
@RequestMapping
public class SearchController {
    private final SearchService searchService;



    @GetMapping("/search")
    public ResponseEntity<SearchPageRes> searchPage(
            @RequestParam(value = "search_word", required = false) String searchWord) {
        SearchPageRes result = searchService.getSearchPage(searchWord);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search-page")
    public ResponseEntity<SearchResponse> getSearchPage() {
        SearchResponse searchResponse = searchService.getSearchPageInfo();
        return ResponseEntity.ok(searchResponse);
    }
}