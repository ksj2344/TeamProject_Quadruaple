package com.green.project_quadruaple.home;

import com.green.project_quadruaple.home.res.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeMapper {
    //home화면 가져오기
    List<RecommendFest> getFestival(long userId);
    List<PopularLocation> getPopularLocation();
    List<RecentStrf> getRecent(long userId);
    List<RecommendStrf> getRecommend(long userId);

    //mypage 가져오기
    //일단 mybatis에서 list로 묶었기 때문에 list로 받긴하는데 단일도 가능한지 확인해볼것.
    MyPageRes pushHamburger(long userId);

}
