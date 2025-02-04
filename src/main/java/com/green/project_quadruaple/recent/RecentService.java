package com.green.project_quadruaple.recent;

import com.green.project_quadruaple.common.config.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecentService {
    private final RecentMapper recentMapper;
    private final AuthenticationFacade authenticationFacade;

    @Transactional
    public int recentHide(Long strfId) {
        Long userId = authenticationFacade.getSignedUserId();
        return recentMapper.recentHide(userId, strfId);
    }

    // 일괄 삭제(숨김) - 변경된 행의 개수 반환
    @Transactional
    public int recentAllHide() {
        Long userId = authenticationFacade.getSignedUserId();
        return recentMapper.recentAllHide(userId);
    }

}
