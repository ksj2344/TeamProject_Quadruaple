package com.green.project_quadruaple.recent;

import com.green.project_quadruaple.recent.model.HideRecentUpdReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecentService {
    private final RecentMapper recentMapper;

    @Transactional
    public int recentHide(HideRecentUpdReq req) {
        return recentMapper.recentHide(req);
    }

    // 일괄 삭제(숨김) - 변경된 행의 개수 반환
    @Transactional
    public int recentAllHide(HideRecentUpdReq req) {
        return recentMapper.recentAllHide(req);
    }

}
