package com.green.project_quadruaple.recent;

import com.green.project_quadruaple.recent.model.HideRecentUpdReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecentMapper {
    int recentHide (HideRecentUpdReq req);
    int recentAllHide (HideRecentUpdReq req);
}
