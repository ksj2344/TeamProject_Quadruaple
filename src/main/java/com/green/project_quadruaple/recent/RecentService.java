package com.green.project_quadruaple.recent;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.config.security.AuthenticationFacade;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.recent.model.RecentGetListRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecentService {
    private final RecentMapper recentMapper;
    private final AuthenticationFacade authenticationFacade;

    @Value("${const.default-review-size}")
    private int size;

    public ResponseWrapper<List<RecentGetListRes>> recentList (int lastIdx){
        Long userId = authenticationFacade.getSignedUserId();
        int more = 1;
        List<RecentGetListRes> res = recentMapper.recentList(userId,lastIdx,size+more);


        if (res.isEmpty()){
            return new ResponseWrapper<>(ResponseCode.BAD_GATEWAY.getCode(), null);
        }

        boolean hasMore = res.size() > size;
        if (hasMore) {
            res.get(res.size() - 1).setMore(true); // 마지막 요소에 isMore = true 설정
            res.remove(res.size() - 1); // 추가된 데이터 삭제
        }

        try {
            return new ResponseWrapper<>(ResponseCode.OK.getCode(), res);
        } catch (Exception e) {
            return new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null);
        }
    }

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
