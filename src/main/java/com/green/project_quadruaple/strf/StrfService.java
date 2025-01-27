package com.green.project_quadruaple.strf;

import com.green.project_quadruaple.common.model.Constants;
import com.green.project_quadruaple.review.model.ReviewSelReq;
import com.green.project_quadruaple.review.model.ReviewSelRes;
import com.green.project_quadruaple.strf.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StrfService {
    private final StrfMapper strfMapper;

    public StrfDto getDetail(StrfSelReq req) {
        StrfDto dto = strfMapper.getDetail(req);
        if (dto == null) {
            throw new IllegalArgumentException("Invalid Strf ID: " + req.getStrfId());
        }
        if (dto.getStrfId() != req.getStrfId()) {
            throw new IllegalArgumentException("Invalid Strf ID: " + req.getStrfId());
        }
        List<StrfSelRes> updatedResList = dto.getRes();
        if (updatedResList == null) {
            updatedResList = Collections.emptyList();
        }
        dto.setRes(updatedResList);
        return dto;
    }

}
