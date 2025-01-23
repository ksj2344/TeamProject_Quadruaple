package com.green.project_quadruaple.strf;

import com.green.project_quadruaple.strf.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class StrfService {
    private final StrfMapper strfMapper;
    private final ModelMapper modelMapper = new ModelMapper(); // ModelMapper 인스턴스 생성


    public StrfDto getDetail(StrfSelReq req) {
        StrfDto dto = strfMapper.getDetail(req);

        if (dto == null || dto.getStrfId() != req.getStrfId()) {
            return null;
        }

        // ModelMapper를 사용하여 매핑
        List<StrfSelRes> updatedResList = dto.getRes() == null ? new ArrayList<>() :
                dto.getRes().stream()
                        .map(item -> modelMapper.map(dto, StrfSelRes.class))
                        .toList();

        dto.setRes(updatedResList);
        return dto;
    }

    public List<StrfSelReviewRes> selReviewListWithCount (StrfSelReviewReq req){
        // DB에서 리뷰 목록 조회
        List<StrfSelReviewRes> reviews = strfMapper.selReviewListWithCount(req);

        int limit = 6;
        // 필요시 limit 적용 (SQL에서 처리 가능하지만 추가로 제어 가능)
        if (limit > 0 && reviews.size() > limit) {
            return reviews.subList(0, limit);
        }
        return reviews;
    }

}
