package com.green.project_quadruaple.search;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.search.model.LocationResponse;
import com.green.project_quadruaple.search.model.SearchPageReq;
import com.green.project_quadruaple.search.model.SearchPageRes;
import com.green.project_quadruaple.search.model.SearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
