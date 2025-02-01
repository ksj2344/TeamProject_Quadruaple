package com.green.project_quadruaple.datamanager;

import com.green.project_quadruaple.datamanager.model.MenuDto;
import com.green.project_quadruaple.datamanager.model.StrfIdGetReq;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface DataMapper {
    //strf_id 목록 찾기
    List<Long> selectStrfId(StrfIdGetReq p);
    //strf 테이블에 pic넣기
    int insStrfPic(List<Map<String, Object>> picAndStrfIds);
    //strf pic 삭제
    int delStrfIdPic(List<Long> strfIds);

    //menu 추가
    int insMenu(List<Map<String, Object>> menuData);
    //menu 삭제
    int delMenu(List<Long> strfIds);
}
