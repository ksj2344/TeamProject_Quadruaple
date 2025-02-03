package com.green.project_quadruaple.strf;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.Constants;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.review.model.ReviewSelReq;
import com.green.project_quadruaple.review.model.ReviewSelRes;
import com.green.project_quadruaple.strf.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StrfService {
    private final StrfMapper strfMapper;

    public ResponseWrapper<StrfDto> getDetail(Long userId, Long strfId) {
        Long effectedUserId = userId != null ? userId : null;
        if (strfId == null){
            return null;
        }
        StrfDto dto = strfMapper.getDetail(effectedUserId , strfId);
        System.out.println("Current user_id: " + userId);


        if (dto == null) {
            return new ResponseWrapper<>(ResponseCode.BAD_GATEWAY.getCode(), null);
        }

        List<StrfSelRes> updatedResList = dto.getRes();

        if (updatedResList == null) {
            updatedResList = Collections.emptyList();
        }
        System.out.println("Current user_id2: " + userId);


        dto.setRes(updatedResList);

        if (dto.getUserId() > 0){
            strfMapper.strfUpsert(dto.getUserId(), dto.getStrfId());
        } else {
            return new ResponseWrapper<>(ResponseCode.BAD_GATEWAY.getCode(), null);
        }
        System.out.println("Current user_id3: " + userId);


        return new ResponseWrapper<>(ResponseCode.OK.getCode(), dto);
    }

}
