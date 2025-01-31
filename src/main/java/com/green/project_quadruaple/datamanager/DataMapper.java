package com.green.project_quadruaple.datamanager;

import com.green.project_quadruaple.datamanager.model.MenuDto;
import com.green.project_quadruaple.datamanager.model.StrfIdGetReq;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface DataMapper {
    //strf 테이블에 pic넣기
    int insStrfPic(List<String> picAndStrfId);
    //strf_id 목록 찾기
    List<Long> selectStrfId(StrfIdGetReq p);
    //strf pic 삭제
    int delStrfIdPic(List<Long> strfIds);

    //menu 추가
    int insMenu(List<String> menuData);
    //menu 삭제
    int delMenu(List<Long> strfIds);
}
