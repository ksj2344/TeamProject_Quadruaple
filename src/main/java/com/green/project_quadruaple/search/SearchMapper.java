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
    void searchIns (@Param("txt") String txt ,@Param("user_id") Long userId);

    // 홈 기본 검색화면 - 최근 본 목록 & 인기 상품
    List<SearchBasicRecentRes> searchBasicRecent(Long userId);
    List<SearchBasicPopualarRes> searchBasicPopular();


    // 검색 -> 전체 상품 탭
//    List<SearchAllList> searchAllList(@Param("search_word") String searchWord
//                                     , @Param("category") String category
//                                     , @Param("user_id") Long userId
//                                     , @Param("amenity_id") List<Long> amenityIds);

    List<Stay> searchAllList(@Param("search_word") String searchWord , @Param("user_id") Long userId);

    // 전체 상품에서 해당 카테고리 전환
    List<Stay> searchCategoryWithFilters(@Param("category") Category category
                                        , @Param("startIdx") int startIdx
                                        , @Param("size") int size
                                        , @Param("userId") Long userId);

    // 숙소 카테고리에서
    List<Stay> searchStayByAmenity(@Param("amenityId") Long amenityId
                                        , @Param("startIdx") int startIdx
                                        , @Param("size") int size
                                        , @Param("userId") Long userId);
//    List<SearchCategoryList> searchCategoryWithFilters(@Param("user_id") Long userId, @Param("category") String category, @Param("amenity_id") List<Long> amenityIds,
//            @Param("start_idx") int startIdx, @Param("size") int size);

}
