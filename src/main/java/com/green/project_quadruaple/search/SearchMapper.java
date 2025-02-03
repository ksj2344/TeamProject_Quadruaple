package com.green.project_quadruaple.search;

import com.green.project_quadruaple.search.model.*;
<<<<<<< HEAD
=======
import com.green.project_quadruaple.search.model.SearchBasicReq;
import com.green.project_quadruaple.search.model.strf_list.LocationIdAndTitleDto;
>>>>>>> 7ae566736101f0cd8002cf83fdfbad25ab3f2d62
import com.green.project_quadruaple.search.model.strf_list.StrfShortInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SearchMapper {
    List<LocationResponse> getTripLocation(@Param("search_word") String searchWord);

    // 일정 추가 검색
    List<StrfShortInfoDto> selStrfShortInfoBasic(long userId, List<LocationIdAndTitleDto> locationIdList, int startIdx, int size, String category, String searchWord);
    List<LocationIdAndTitleDto> selLocationIdByTripId(long tripId);


    List<SearchBasicRecentRes> searchBasicRecent(Long userId);
    List<SearchBasicPopualarRes> searchBasicPopular();

    List<SearchAllList> searchAllList(@Param("search_word") String searchWord
                                     , @Param("category") String category
                                     , @Param("user_id") Long userId
                                     , @Param("amenity_id") List<Long> amenityIds);

    List<SearchCategoryList> searchCategoryWithFilters(@Param("user_id") Long userId, @Param("category") String category, @Param("amenity_id") List<Long> amenityIds,
            @Param("start_idx") int startIdx, @Param("size") int size);



    /*
    검색 창 터치 -> 검색 목록 출력
    <select id="searchGetList">
        SELECT txt
        FROM search_word
        WHERE user_id = #{userId}
    </select>

    검색 창 입력 -> 검색창 테이블에 검색 데이터 입력
    <insert id="searchIns">
        INSERT INTO search_word (txt , user_id)
        VALUES (#{txt} , #{userId})
    </insert>
     */

}
