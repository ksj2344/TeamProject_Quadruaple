package com.green.project_quadruaple.search;

import com.green.project_quadruaple.common.config.enumdata.StrfCategory;
import com.green.project_quadruaple.search.model.*;
import com.green.project_quadruaple.search.model.SearchBasicReq;
import com.green.project_quadruaple.search.model.filter.Stay;
import com.green.project_quadruaple.search.model.filter.StaySearchReq;
import com.green.project_quadruaple.search.model.strf_list.LocationIdAndTitleDto;
import com.green.project_quadruaple.search.model.strf_list.StrfShortInfoDto;
import com.green.project_quadruaple.trip.model.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SearchMapper {
    List<LocationResponse> getTripLocation(@Param("search_word") String searchWord);

    // 일정 추가 검색
    List<StrfShortInfoDto> selStrfShortInfoBasic(long userId, List<LocationIdAndTitleDto> locationIdList, int startIdx, int size, String category, String searchWord);
    List<LocationIdAndTitleDto> selLocationIdByTripId(long tripId);




    // 검색창 출력 + 검색어 저장
    List<SearchGetRes> searchGetList (Long userId);
    void searchIns (@Param("search_word")String searchWord ,Long userId);
    // 홈 기본 검색화면 - 최근 본 목록 & 인기 상품
    List<SearchBasicRecentRes> searchBasicRecent(Long userId);
    List<SearchBasicPopualarRes> searchBasicPopular();
    List<Stay> searchAllList(@Param("search_word") String searchWord, Long userId);
    // 전체 상품에서 해당 카테고리 전환
    List<Stay> searchCategoryWithFilters(@Param("category") Category category
                                        , @Param("start_idx") int startIdx
                                        , @Param("size") int size
                                        , Long userId);
    // 숙소 카테고리에서
    List<Stay> searchStayByAmenity(@Param("amenity_id") Long amenityId
                                        , @Param("start_idx") int startIdx
                                        , @Param("size") int size
                                        , Long userId);
}
