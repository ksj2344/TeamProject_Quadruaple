package com.green.project_quadruaple.datamanager;

import com.green.project_quadruaple.datamanager.model.StrfPicReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PicMapper {
    //strf 테이블에 pic넣기
    int insStrfPic(List<String> picAndStrfId);
    //strf_id 목록 찾기
    List<Long> selectStrfId(StrfPicReq p);
    //strf pic 삭제
    int delStrfIdPic(List<Long> strfIds);
}
