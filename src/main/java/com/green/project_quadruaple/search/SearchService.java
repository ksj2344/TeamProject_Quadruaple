package com.green.project_quadruaple.search;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.Paging;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.search.model.*;
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


    public ResponseWrapper<List<SearchBasicRes>> searchBasicList(SearchBasicReq request) {
        try {
            List<SearchBasicRes> resList = searchMapper.searchBasicList(request);
            return new ResponseWrapper<>(ResponseCode.OK.getCode(), resList);
        } catch (Exception e) {
            log.error("Error fetching search basic list: ", e);
            return new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null);
        }
    }

    public ResponseWrapper<List<SearchAllList>> searchAllList(String searchWord, String category, Long userId, Paging paging) {
        try {
            List<SearchAllList> result = searchMapper.searchAllList(searchWord, category, userId, paging.getStartIdx(), paging.getSize());

            boolean isMore = result.size() > paging.getSize();
            if (isMore) {
                result = result.subList(0, paging.getSize());
            }

            return new ResponseWrapper<>(ResponseCode.OK.getCode(), result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseWrapper<>(ResponseCode.BAD_GATEWAY.getCode(), null);
        }
    }

    public ResponseWrapper<List<SearchCategoryList>> searchCategoryWithFilters(Long userId, String category, List<Long> amenityIds, Paging paging) {
        try {
            List<SearchCategoryList> result = searchMapper.searchCategoryWithFilters(userId, category, amenityIds, paging.getStartIdx(), paging.getSize());

            boolean isMore = result.size() > paging.getSize();
            if (isMore) {
                result = result.subList(0, paging.getSize());
            }

            return new ResponseWrapper<>(ResponseCode.OK.getCode(), result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseWrapper<>(ResponseCode.BAD_GATEWAY.getCode(), null);
        }
    }
}
