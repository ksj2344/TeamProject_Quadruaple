package com.green.project_quadruaple.review;

import com.green.project_quadruaple.common.MyFileUtils;
import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.review.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;
    private final MyFileUtils myFileUtils;

    public List<ReviewSelRes> getReview(ReviewSelReq req) {
        log.info("Received strfId: " + req.getStrfId());
//        ReviewSelRes res = reviewMapper.getReview(req.getStrfId());
//        res.getStrfId();
        List<ReviewSelRes> res = reviewMapper.getReview(req);
        if (req.getStartIdx()<0){
            res = new ArrayList<>();
            return res;
        }

        return res;
    }
//    public List<FeedGetRes> getFeedList4(FeedGetReq p) {
//        List<FeedWithPicCommentDto> dtoList = feedMapper.selFeedWithPicAndCommentLimit4List(p);
//        List<FeedGetRes> list = new ArrayList<>(dtoList.size());
//        for(FeedWithPicCommentDto item : dtoList) {
//            list.add(new FeedGetRes(item));
//        }
//        return list;
//    }

    @Transactional
    public void postRating(ReviewPostDto dto) {
        reviewMapper.postRating(dto);
        long reviewId = dto.getReviewId();

        // 2. 사진 저장 및 파일명 변경
        List<MultipartFile> reviewPics = dto.getPics();
        if (reviewPics != null && !reviewPics.isEmpty()) {
            List<String> savedFileNames = new ArrayList<>();
            String uploadFolder = "review/" + reviewId; // 저장할 폴더 경로

            for (MultipartFile pic : reviewPics) {
                if (!pic.isEmpty()) {
                    try {
                        // 랜덤 파일명 생성
                        String savedFileName = myFileUtils.makeRandomFileName(pic);
                        String savePath = uploadFolder + "/" + savedFileName; // 파일 저장 경로

                        // 파일 저장
                        myFileUtils.transferTo(pic, savePath);

                        // DB에 저장할 파일명 리스트
                        savedFileNames.add(savedFileName);
                    } catch (Exception e) {
                        throw new RuntimeException("파일 저장 실패: " + e.getMessage());
                    }
                }
            }

            // 3. DB에 저장
            if (!savedFileNames.isEmpty()) {
                reviewMapper.postReviewPics(reviewId, savedFileNames);
            }
        }
    }

    public ResponseWrapper<ReviewUpdRes> updateReview(ReviewUpdReq req) {
        int updatedRows = reviewMapper.patchReview(req);

        if (updatedRows == 0) {
            throw new IllegalArgumentException("리뷰 수정 실패: 해당 리뷰를 찾을 수 없거나 수정 권한이 없습니다.");
        }

        ReviewUpdRes res = new ReviewUpdRes();
        res.setReviewId(req.getReviewId());

        return new ResponseWrapper<>(ResponseCode.OK.getCode(), res);
    }


}
