package com.green.project_quadruaple.search;

import com.green.project_quadruaple.search.model.LocationResponse;
import com.green.project_quadruaple.search.model.SearchPageReq;
import com.green.project_quadruaple.search.model.SearchPageRes;
import com.green.project_quadruaple.search.model.SearchResponse;
import com.green.project_quadruaple.trip.model.dto.LocationDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SearchMapper {
    List<LocationResponse> getTripLocation(@Param("search_word") String searchWord);

}
