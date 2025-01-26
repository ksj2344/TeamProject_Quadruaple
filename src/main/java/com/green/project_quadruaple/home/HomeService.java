package com.green.project_quadruaple.home;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.home.res.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeService {
    private final HomeMapper homeMapper;

    //홈화면
    public ResponseEntity<ResponseWrapper<HomeRes>> getHome(){
        long userId=101L;
        List<RecommendFest> recommendFests=homeMapper.getFestival(userId);
        List<RecentStrf> recentStrfs=homeMapper.getRecent(userId);
        List<RecommendStrf> recommendStrfs=homeMapper.getRecommend(userId);
        List<PopularLocation> popularLocations=homeMapper.getPopularLocation();

        HomeRes homeRes=new HomeRes(recommendFests,popularLocations,recentStrfs,recommendStrfs);

        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),homeRes));
    }

    //마이페이지
    public ResponseEntity<ResponseWrapper<List<MyPageRes>>> myPage(){
        long userId=101L;
        List<MyPageRes> myPageRes=homeMapper.pushHamburger(userId);
        if(myPageRes==null){
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(), null));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),myPageRes));
    }
}
