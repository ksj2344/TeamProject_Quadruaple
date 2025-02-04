package com.green.project_quadruaple.search;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.config.enumdata.StrfCategory;
import com.green.project_quadruaple.common.model.Paging;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.search.model.*;
import com.green.project_quadruaple.search.model.filter.Stay;
import com.green.project_quadruaple.search.model.filter.StaySearchReq;
import com.green.project_quadruaple.search.model.filter.StaySearchRes;
import com.green.project_quadruaple.search.model.strf_list.GetSearchStrfListBasicRes;
import com.green.project_quadruaple.search.model.SearchBasicReq;
import com.green.project_quadruaple.search.model.strf_list.LocationIdAndTitleDto;
import com.green.project_quadruaple.search.model.strf_list.StrfShortInfoDto;
import com.green.project_quadruaple.trip.model.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchMapper searchMapper;

    @Value("${const.default-review-size}")
    private int size;

    // 기존
    /*public List<LocationResponse> getTripLocation(String searchWord, int offset, int pageSize) {
        // SQL 매퍼 호출
        return searchMapper.getTripLocation(searchWord, offset, pageSize);
    }*/


   /* public List<LocationResponse> getTripLocation(String searchWord) {
        return searchMapper.getTripLocation(searchWord);
    }*/
   public List<LocationResponse> getTripLocation(String searchWord) {
       List<LocationResponse> locations = searchMapper.getTripLocation(searchWord);
       System.out.println("Fetched locations: " + locations); // 디버깅용 로그 추가
       return locations;
   }

    public ResponseWrapper<GetSearchStrfListBasicRes> getStrfListBasic(long tripId, int lastIdx) {
        if(tripId == 0) return new ResponseWrapper<>(ResponseCode.BAD_REQUEST.getCode(), null);
        long signedUserId = 101L;
        int more = 1;
        try {
            List<LocationIdAndTitleDto> locationIdList = searchMapper.selLocationIdByTripId(tripId);
            List<StrfShortInfoDto> dto = searchMapper.selStrfShortInfoBasic(signedUserId, locationIdList, lastIdx, size+more, null, null);
            GetSearchStrfListBasicRes res = new GetSearchStrfListBasicRes();
            if(dto.size() >= size) {
                res.setMore(true);
            }
            res.setList(dto);
            List<String> titleList = new ArrayList<>();
            for(LocationIdAndTitleDto list : locationIdList) {
                titleList.add(list.getLocationTitle());
            }
            res.setLocationTitleList(titleList);
            return new ResponseWrapper<>(ResponseCode.OK.getCode(), res);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public ResponseWrapper<GetSearchStrfListBasicRes> getStrfListWithSearchWord(long tripId,
                                                                                int lastIdx,
                                                                                String category,
                                                                                String searchWord)
    {
        if(tripId == 0) return new ResponseWrapper<>(ResponseCode.BAD_REQUEST.getCode(), null);
        String categoryValue = null;
        if(category != null && Category.getKeyByName(category) != null) {
            categoryValue = Objects.requireNonNull(Category.getKeyByName(category)).getValue();
        }
        long signedUserId = 101L;
        int more = 1;
        try {
            List<LocationIdAndTitleDto> locationIdList = searchMapper.selLocationIdByTripId(tripId);
            List<StrfShortInfoDto> dto = searchMapper.selStrfShortInfoBasic(signedUserId, locationIdList, lastIdx, size+more, categoryValue, searchWord);
            GetSearchStrfListBasicRes res = new GetSearchStrfListBasicRes();
            if(dto.size() >= size) {
                res.setMore(true);
            }
            res.setList(dto);
            return new ResponseWrapper<>(ResponseCode.OK.getCode(), res);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

// 밑으로 상품 검색

    public ResponseWrapper<List<SearchBasicRecentRes>> searchBasicRecent(Long userId) {
        Long effectedUserId = (userId != null) ? userId : null;
        List<SearchBasicRecentRes> res = searchMapper.searchBasicRecent(effectedUserId);
        if (res.isEmpty()){
            return new ResponseWrapper<>(ResponseCode.BAD_GATEWAY.getCode(), null);
        }
        try {
            return new ResponseWrapper<>(ResponseCode.OK.getCode(), res);
        } catch (Exception e) {
            return new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null);
        }
    }

    public ResponseWrapper<List<SearchBasicPopualarRes>> searchBasicPopular(){
        List<SearchBasicPopualarRes> res = searchMapper.searchBasicPopular();
        if (res.isEmpty()){
            return new ResponseWrapper<>(ResponseCode.BAD_GATEWAY.getCode(), null);
        }
        try {
            return new ResponseWrapper<>(ResponseCode.OK.getCode(), res);
        } catch (Exception e) {
            return new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null);
        }
    }



//    public ResponseWrapper<List<SearchAllList>> searchAllList(String searchWord , String category, Long userId, List<Long> amenityIds) {
//        Long effectiveUserId = (userId != null) ? userId : null;
//
//        List<Long> effectiveAmenityIds = (amenityIds != null && !amenityIds.isEmpty()) ? amenityIds : Collections.emptyList();
//
//        List<SearchAllList> result = searchMapper.searchAllList(searchWord ,category, effectiveUserId, effectiveAmenityIds);
//
//        try {
//            return new ResponseWrapper<>(ResponseCode.OK.getCode(), result);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//            return new ResponseWrapper<>(ResponseCode.BAD_GATEWAY.getCode(), null);
//        }
//    }

    public StaySearchRes searchAll(StaySearchReq request) {
        List<Stay> stays = searchMapper.searchAllList(request);
        int totalCount = stays.size(); // 총 개수 계산 (필요시 수정)

        StaySearchRes response = new StaySearchRes();
        response.setStays(stays);
        response.setTotalCount(totalCount);
        return response;
    }

    public List<Stay> searchCategoryWithFilters(StrfCategory category, int startIdx, int size, Long userId) {
        return searchMapper.searchCategoryWithFilters(category, startIdx, size, userId);
    }

    public List<Stay> searchStayByAmenity(Long amenityId, int startIdx, int size, Long userId) {
        return searchMapper.searchStayByAmenity(amenityId, startIdx, size, userId);
    }
//    public ResponseWrapper<List<SearchCategoryList>> searchCategoryWithFilters(String searchWord , String category, Long userId, List<Long> amenityIds) {
//        try {
//            List<SearchCategoryList> result = searchMapper.searchCategoryWithFilters(searchWord ,category, userId,  amenityIds);
//
//            boolean isMore = result.size() > paging.getSize();
//            if (isMore) {
//                result = result.subList(0, paging.getSize());
//            }
//
//            return new ResponseWrapper<>(ResponseCode.OK.getCode(), result);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//            return new ResponseWrapper<>(ResponseCode.BAD_GATEWAY.getCode(), null);
//        }
//    }

}
