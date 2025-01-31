package com.green.project_quadruaple.review;

import com.green.project_quadruaple.common.MyFileUtils;
import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.config.security.AuthenticationFacade;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.review.model.*;
import com.green.project_quadruaple.user.exception.CustomException;
import com.green.project_quadruaple.user.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;

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

    @Transactional
    public ResponseEntity<ResponseWrapper<Integer>> postRating(List<MultipartFile> pics, ReviewPostReq p) {
        p.setReviewId(authenticationFacade.getSignedUserId());
        p.getContent();

        if (p.getReviewId() <= 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>(ResponseCode.NOT_FOUND.getCode(),null));
        }
        int result = reviewMapper.postRating(p);
        if (result==0){
            ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
        }
        long reviewId = p.getReviewId();
        String middlePath = String.format("reviewId/%d",reviewId);
        myFileUtils.makeFolders(middlePath);
        List<String> picNameList = new ArrayList<>(pics.size());
        for (MultipartFile pic : pics){
            String savedPicName = myFileUtils.makeRandomFileName(pic);
            picNameList.add(savedPicName);
            String filePath = String.format("%s/%s",middlePath,savedPicName);
            try {
                myFileUtils.transferTo(pic, filePath);
            } catch (IOException e) {
                //폴더 삭제 처리
                String delFolderPath = String.format("%s/%s", myFileUtils.getUploadPath(), middlePath);
                myFileUtils.deleteFolder(delFolderPath, true);
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body(new ResponseWrapper<>(ResponseCode.SERVER_ERROR.getCode(), null));
            }
        }
        ReviewPicDto reviewPicDto = new ReviewPicDto();
        reviewPicDto.setReviewId(reviewId);
        reviewPicDto.setPics(picNameList);
        int resultPics = reviewMapper.postReviewPic(reviewPicDto);
        return ResponseEntity.ok(new ResponseWrapper<>(ResponseCode.OK.getCode(),resultPics));
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
