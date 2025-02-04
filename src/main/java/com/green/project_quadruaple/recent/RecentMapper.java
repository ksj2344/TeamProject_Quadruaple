package com.green.project_quadruaple.recent;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RecentMapper {
    int recentHide (Long userId , @Param("strf_id") Long strfId);
    int recentAllHide (Long userId);
}
