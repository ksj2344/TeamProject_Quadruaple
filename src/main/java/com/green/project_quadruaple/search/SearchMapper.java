package com.green.project_quadruaple.search;

import com.green.project_quadruaple.search.model.*;
import com.green.project_quadruaple.search.model.SearchBasicReq;
import com.green.project_quadruaple.search.model.strf_list.StrfShortInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SearchMapper {
    List<LocationResponse> getTripLocation(@Param("search_word") String searchWord);

    // 일정 추가 검색
    List<StrfShortInfoDto> selStrfShortInfoBasic(long userId, List<Long> locationIdList, int startIdx, int size, String category, String searchWord);
    List<Long> selLocationIdByTripId(long tripId);


    List<SearchBasicRes> searchBasicList(SearchBasicReq request);

    List<SearchAllList> searchAllList(@Param("search_word") String searchWord, @Param("category") String category, @Param("user_id") Long userId,
            @Param("start_idx") int startIdx, @Param("size") int size);
    List<SearchCategoryList> searchCategoryWithFilters(@Param("user_id") Long userId, @Param("category") String category, @Param("amenity_id") List<Long> amenityIds,
            @Param("start_idx") int startIdx, @Param("size") int size);

}
