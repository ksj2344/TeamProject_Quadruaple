package com.green.project_quadruaple.picmanager;

import com.green.project_quadruaple.picmanager.model.StrfPicReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PicMapper {
    //테이블에 pic넣기
    int insStrfPic(String pic,List<Long> strfList);
    //넣을 strf_id 찾기
    List<Long> selectStrfId(StrfPicReq p);
}
