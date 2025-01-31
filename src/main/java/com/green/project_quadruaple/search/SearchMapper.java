package com.green.project_quadruaple.search;

import com.green.project_quadruaple.search.model.SearchPageReq;
import com.green.project_quadruaple.search.model.SearchPageRes;
import com.green.project_quadruaple.search.model.SearchResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SearchMapper {
    SearchPageRes searchPage(@Param("searchWord") String searchWord);
    List<SearchResponse> searchResults(Map<String, Object> params); // 검색 결과 조회
    SearchResponse getSearchPageInfo();
    Map<String, Object> getTripLocation(@Param("search_word") String searchWord);
    
}
