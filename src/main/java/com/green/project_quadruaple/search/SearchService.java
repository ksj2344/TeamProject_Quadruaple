package com.green.project_quadruaple.search;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.search.model.LocationResponse;
import com.green.project_quadruaple.search.model.SearchPageReq;
import com.green.project_quadruaple.search.model.SearchPageRes;
import com.green.project_quadruaple.search.model.SearchResponse;
import com.green.project_quadruaple.search.model.strf_list.GetSearchStrfListBasicRes;
import com.green.project_quadruaple.search.model.strf_list.StrfShortInfoDto;
import com.green.project_quadruaple.trip.TripMapper;
import com.green.project_quadruaple.trip.model.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {
    private final SearchMapper searchMapper;


    @Value("${const.default-review-size}")
    private int size;

    public SearchPageRes getSearchPage(String searchWord) {
        return searchMapper.searchPage(searchWord);
    }

    public SearchResponse getSearchPageInfo() {
        return searchMapper.getSearchPageInfo();
    }


    public Map<String, Object> getTripLocation(String searchWord) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> location = searchMapper.getTripLocation(searchWord);

        if (location == null) {
            // 검색 결과가 없을 때
            response.put("code", ResponseCode.NOT_FOUND.getCode());
            response.put("message", "검색 결과가 없습니다.");
        } else {
            // 검색 결과 반환
            response.put("code", ResponseCode.OK.getCode());
            response.putAll(location);
        }
        return response;
    }


    public LocationResponse getLocation(String searchWord) {
        Map<String, Object> location = searchMapper.getTripLocation(searchWord);
        if (location == null) {
            return new LocationResponse("404", null, null, null); // 검색 결과 없음
        }
        return new LocationResponse(
                "200",
                (String) location.get("locationTitle"),
                (String) location.get("locationPic"),
                (Integer) location.get("locationId")
        );
    }

    public ResponseWrapper<GetSearchStrfListBasicRes> getStrfListBasic(long tripId, int lastIdx) {
        if(tripId == 0) return new ResponseWrapper<>(ResponseCode.BAD_REQUEST.getCode(), null);
        long signedUserId = 101L;
        int more = 1;
        try {
            List<Long> locationIdList = searchMapper.selLocationIdByTripId(tripId);
            List<StrfShortInfoDto> dto = searchMapper.selStrfShortInfoBasic(signedUserId, locationIdList, lastIdx, size+more, null, null);
            GetSearchStrfListBasicRes res = new GetSearchStrfListBasicRes();
            if(dto.size() >= size) {
                res.setMore(true);
            }
            res.setList(dto);
            return new ResponseWrapper<>(ResponseCode.OK.getCode(), res);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException();
        }
    }

    public ResponseWrapper<GetSearchStrfListBasicRes> getStrfListWithSearchWord(long tripId,
                                                                                int lastIdx,
                                                                                String category,
                                                                                String searchWord)
    {
        if(tripId == 0) return new ResponseWrapper<>(ResponseCode.BAD_REQUEST.getCode(), null);
        String categoryValue = Optional.ofNullable(Category.getKeyByName(category)).orElseThrow(RuntimeException::new).getValue();
        long signedUserId = 101L;
        int more = 1;
        try {
            List<Long> locationIdList = searchMapper.selLocationIdByTripId(tripId);
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
}
