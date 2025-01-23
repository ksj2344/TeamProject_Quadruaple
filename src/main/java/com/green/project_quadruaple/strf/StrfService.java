package com.green.project_quadruaple.strf;

import com.green.project_quadruaple.common.model.Constants;
import com.green.project_quadruaple.strf.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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

        if (dto == null || dto.getStrfId() != req.getStrfId()) {
            throw new IllegalArgumentException("Invalid Strf ID: " + req.getStrfId());
        }

        List<StrfSelRes> updatedResList = Optional.ofNullable(dto.getRes())
                .orElse(Collections.emptyList());

        dto.setRes(updatedResList);
        return dto;
    }

    public List<ReviewSelRes> selReviewListWithCount(ReviewSelReq req) {
        List<ReviewSelRes> reviewList = strfMapper.selReviewListWithCount(req);

        if (reviewList.isEmpty()) {
            return reviewList;
        }

        List<Long> reviewIds = reviewList.stream()
                .map(ReviewSelRes::getReviewId)
                .collect(Collectors.toList());

        List<StrfPicSel> strfPicSelList = strfMapper.selReviewPicsByReviewIds(reviewIds);

        Map<Long, List<String>> picMap = strfPicSelList.stream()
                .collect(Collectors.groupingBy(
                        StrfPicSel::getStrfId,
                        Collectors.mapping(StrfPicSel::getPic, Collectors.toList())
                ));

        for (ReviewSelRes review : reviewList) {
            review.setPictures(picMap.getOrDefault(review.getReviewId(), Collections.emptyList()));
        }

        boolean hasMore = reviewList.size() > Constants.getDefault_page_size();
        if (hasMore) {
            reviewList = reviewList.subList(0, Constants.getDefault_page_size());
            ReviewSelRes moreItem = new ReviewSelRes();
            moreItem.setMoreReview(true);
            reviewList.add(moreItem);
        }

        return reviewList;
    }
}
