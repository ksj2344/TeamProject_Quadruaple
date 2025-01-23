package com.green.project_quadruaple.search;

import com.green.project_quadruaple.search.model.SearchPageReq;
import com.green.project_quadruaple.search.model.SearchPageRes;
import com.green.project_quadruaple.search.model.SearchResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SearchMapper {
    SearchPageRes searchPage(@Param("searchWord") String searchWord);

    SearchResponse getSearchPageInfo();
}
