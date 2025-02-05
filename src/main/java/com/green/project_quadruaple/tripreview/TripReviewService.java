package com.green.project_quadruaple.tripreview;

import com.green.project_quadruaple.common.MyFileUtils;
import com.green.project_quadruaple.common.config.security.AuthenticationFacade;
import com.green.project_quadruaple.trip.TripMapper;
import com.green.project_quadruaple.trip.model.req.PostTripReq;
import com.green.project_quadruaple.tripreview.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripReviewService {
    private final TripReviewMapper tripReviewMapper;
    private final TripMapper tripMapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;

    // 여행기 등록
    public TripReviewPostRes postTripReview(List<MultipartFile> tripReviewPic, TripReviewPostReq req) {
        req.setUserId(authenticationFacade.getSignedUserId());
        tripReviewMapper.insTripReview(req);


        // 파일 등록
        long tripReviewId = req.getTripReviewId();
        String middlePath = String.format("tripReview/%d", tripReviewId);
        myFileUtils.makeFolders(middlePath);

        List<String> picNameList = new ArrayList<>();
        // 사진 파일이 있을 경우만 처리
        if (tripReviewPic != null && !tripReviewPic.isEmpty()) {
            myFileUtils.makeFolders(middlePath);

            for (MultipartFile pic : tripReviewPic) {
                String savedPicName = myFileUtils.makeRandomFileName(pic);
                picNameList.add(savedPicName);
                String filePath = String.format("%s/%s", middlePath, savedPicName);
                try {
                    myFileUtils.transferTo(pic, filePath);
                } catch (IOException e) {
                    // 업로드된 폴더 삭제
                    String delFolderPath = String.format("%s/%s", myFileUtils.getUploadPath(), middlePath);
                    myFileUtils.deleteFolder(delFolderPath, true);
                    throw new RuntimeException(e);
                }
            }

            // 사진 파일이 있는 경우에만 DB 저장
            if (!picNameList.isEmpty()) {
                tripReviewMapper.insTripReviewPic(tripReviewId, picNameList);
            }
        }

        TripReviewPostRes tripReviewPostRes = new TripReviewPostRes();
        tripReviewPostRes.setTripReviewId(tripReviewId);
        tripReviewPostRes.setTripReviewPic(picNameList);


        return tripReviewPostRes;
    }

    //여행기 조회
    // 로그인한 사용자의 여행기만 조회
    public List<TripReviewGetDto> getMyTripReviews(String orderType) {
        long userId = authenticationFacade.getSignedUserId(); // 현재 로그인한 유저 ID 가져오기
        return tripReviewMapper.getMyTripReviews(userId, orderType);
    }
    // 모든 사용자의 여행기 조회
    public List<TripReviewGetDto> getAllTripReviews(String orderType) {
        return tripReviewMapper.getAllTripReviews(orderType);
    }
    // 다른 사용자의 여행기 조회
    public TripReviewGetDto getOtherTripReviews(long tripReviewId) {
        long userId = authenticationFacade.getSignedUserId();

        // 여행기 조회수 삽입
        tripReviewMapper.insTripReviewRecent(userId, tripReviewId);

        TripReviewGetDto tripReviewGetDto = tripReviewMapper.getOtherTripReviewById(tripReviewId);
        if (tripReviewGetDto == null) {
            throw new RuntimeException("해당 여행기를 찾을 수 없습니다.");
        }

        return tripReviewGetDto;
    }

    // 여행기 수정
    public int patchTripReview(List<MultipartFile> tripPic, TripReviewPatchDto dto) {
        dto.setUserId(authenticationFacade.getSignedUserId());

        int result = tripReviewMapper.updTripReview(dto);
        if (result == 0) {
            throw new RuntimeException("여행기 수정에 실패했습니다.");
        }

        if (tripPic != null && !tripPic.isEmpty()) {
            // 기존 DB의 여행기 사진 삭제
            tripReviewMapper.delTripReviewPic(dto.getTripReviewId());

            String basePath = myFileUtils.getUploadPath(); // 기본 업로드 경로
            String middlePath = String.format("tripReview/%d", dto.getTripReviewId());
            String targetPath = String.format("%s/%s", basePath, middlePath); // 중복 경로 방지

            myFileUtils.deleteFolder(targetPath, true);

            File newFolder = new File(targetPath);
            if (!newFolder.exists()) {
                boolean created = newFolder.mkdirs();
                if (!created) {
                    throw new RuntimeException("여행기 사진 폴더 생성에 실패했습니다: " + targetPath);
                }
            }

            List<String> picNameList = new ArrayList<>();


            for (MultipartFile pic : tripPic) {
                if (pic != null && !pic.isEmpty()) {
                    String savedPicName = myFileUtils.makeRandomFileName(pic);
                    picNameList.add(savedPicName);
                    String filePath = String.format("%s/%s", middlePath, savedPicName); // 중복 경로 수정

                    try {
                        myFileUtils.transferTo(pic, filePath);
                    } catch (IOException e) {
                        throw new RuntimeException("여행기 사진 저장에 실패했습니다.", e);
                    }
                }
            }

            if (!picNameList.isEmpty()) {
                tripReviewMapper.insTripReviewPic(dto.getTripReviewId(), picNameList);
            } else {
                System.out.println("저장할 사진이 없음!");
            }
        }

        return result;
    }

    //여행기 삭제
    public int deleteTripReview(long tripReviewId) {
        long signedUserId = authenticationFacade.getSignedUserId();

        TripReviewDeleteDto tripReviewDeleteDto = tripReviewMapper.selTripReviewByUserId(tripReviewId);

        if (tripReviewDeleteDto == null || tripReviewDeleteDto.getUserId() != signedUserId) {
            return 0;
        }

        tripReviewMapper.delTripReviewLikeByTripReviewId(tripReviewId);

        tripReviewMapper.delTripReviewPic(tripReviewId);
        String basePath = myFileUtils.getUploadPath(); // 기본 업로드 경로
        String middlePath = String.format("tripReview/%d", tripReviewId);
        String targetPath = String.format("%s/%s", basePath, middlePath); // 중복 경로 방지
        myFileUtils.deleteFolder(targetPath, true);

        return tripReviewMapper.delTripReview(tripReviewId);
    }


    // 여행기 추천
    public int insTripLike(TripLikeDto like) {
        return tripReviewMapper.insTripLike(like);
    }

    public int delTripLike(TripLikeDto like) {
        return tripReviewMapper.delTripLike(like);
    }

    public int getTripLikeCount(Long tripReviewId) {
        return Optional.ofNullable(tripReviewMapper.tripLikeCount(tripReviewId)).orElse(0);
    }

    // 여행기 스크랩
    public int copyTripReview(CopyInsertTripDto trip) {
        long userId = authenticationFacade.getSignedUserId();
        trip.setUserId(userId);

        // 1. 여행 복사
        int copyTrip = tripReviewMapper.copyInsTrip(trip);

        // 2. 일정/메모 복사
        CopyInsertScheMemoDto copyInsertScheMemoDto = new CopyInsertScheMemoDto();
        copyInsertScheMemoDto.setTripId(trip.getTripId());
        copyInsertScheMemoDto.setCopyTripId(trip.getCopyTripId());
        int copyScheMemo = tripReviewMapper.copyInsScheMemo(copyInsertScheMemoDto);

        // 3. 기존 sche_memo_id 목록 조회 (originalScheMemoIds 찾기)
        List<Long> originalScheMemoIds = tripReviewMapper.getOriginalScheMemoIds(trip.getCopyTripId());

        // 3-1. 새롭게 생성된 scheduleMemoId 목록 조회
        List<Long> newScheMemoIds = tripReviewMapper.getNewScheMemoIds(trip.getTripId());

        // 4. 기존 일정의 schedule_id 목록 조회
        List<Long> originalScheduleIds = tripReviewMapper.getOriginalScheduleIds(originalScheMemoIds);

        // 5. 기존 일정 개수와 새로 복사된 일정 개수가 같은지 확인
        if (originalScheduleIds.size() != newScheMemoIds.size()) {
            throw new RuntimeException("Mismatch in schedule_memo mapping. Expected: " + originalScheduleIds.size() + ", but got: " + newScheMemoIds.size());
        }

        // 6. 일정 복사
        for (int i = 0; i < originalScheduleIds.size(); i++) {
            CopyScheduleDto copyScheduleDto = new CopyScheduleDto();
            copyScheduleDto.setScheduleMemoId(newScheMemoIds.get(i)); // 새로 생성된 scheduleMemoId 사용
            copyScheduleDto.setOriginalScheduleId(originalScheduleIds.get(i)); // 기존 schedule_id 사용

            int copySchedule = tripReviewMapper.copyInsSchedule(copyScheduleDto);
        }

        // 7. 여행 위치 복사
        int originalLocationIds = tripReviewMapper.getOriginalLocationIds(trip.getCopyTripId());
        if (!originalScheduleIds.isEmpty()) {
            tripReviewMapper.copyInsTripLocation(trip.getCopyTripId(), trip.getTripId());
        }

        // 8. 스크랩 테이블에 담기
        TripReviewScrapDto tripReviewScrapDto = new TripReviewScrapDto();
        tripReviewScrapDto.setTripReviewId(trip.getTripReviewId());
        tripReviewScrapDto.setTripId(trip.getTripId());
        int insScrap = tripReviewMapper.insTripReviewScrap(tripReviewScrapDto);

        return 1;
    }

}
