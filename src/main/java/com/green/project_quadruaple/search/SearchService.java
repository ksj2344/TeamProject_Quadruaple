package com.green.project_quadruaple.search;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.search.model.LocationResponse;
import com.green.project_quadruaple.search.model.SearchPageReq;
import com.green.project_quadruaple.search.model.SearchPageRes;
import com.green.project_quadruaple.search.model.SearchResponse;
import com.green.project_quadruaple.trip.model.dto.LocationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchMapper searchMapper;

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
}




