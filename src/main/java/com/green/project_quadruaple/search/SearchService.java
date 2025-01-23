package com.green.project_quadruaple.search;

import com.green.project_quadruaple.search.model.SearchPageReq;
import com.green.project_quadruaple.search.model.SearchPageRes;
import com.green.project_quadruaple.search.model.SearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {
    private final SearchMapper searchMapper;

    public SearchPageRes getSearchPage(String searchWord) {
        return searchMapper.searchPage(searchWord);
    }

    public SearchResponse getSearchPageInfo() {
        return searchMapper.getSearchPageInfo();
    }
}
