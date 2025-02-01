package com.green.project_quadruaple.search;

import com.green.project_quadruaple.search.model.SearchPageReq;
import com.green.project_quadruaple.search.model.SearchPageRes;
import com.green.project_quadruaple.search.model.SearchResponse;
import com.green.project_quadruaple.search.model.strf_list.StrfShortInfoDto;
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

    // 일정 추가 검색
    List<StrfShortInfoDto> selStrfShortInfoBasic(long userId, List<Long> locationIdList, int startIdx, int size, String category, String searchWord);
    List<Long> selLocationIdByTripId(long tripId);
}
