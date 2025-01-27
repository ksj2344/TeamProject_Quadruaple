package com.green.project_quadruaple.trip;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.project_quadruaple.trip.model.PubTransPathVo;
import jdk.jfr.ContentType;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import com.green.project_quadruaple.common.config.constant.OdsayApiConst;
import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.common.model.ResultResponse;
import com.green.project_quadruaple.trip.model.dto.*;
import com.green.project_quadruaple.trip.model.req.PatchTripReq;
import com.green.project_quadruaple.trip.model.req.PostStrfScheduleReq;
import com.green.project_quadruaple.trip.model.req.PostTripReq;
import com.green.project_quadruaple.trip.model.res.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripService {

    private final TripMapper tripMapper;
    private final OdsayApiConst odsayApiConst;
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public ResponseWrapper<MyTripListRes> getMyTripList() {
//        long signedUserId = AuthenticationFacade.getSignedUserId();
        long signedUserId = 101L;
        long now = new Date().getTime();
        List<TripDto> TripList = tripMapper.getTripList(signedUserId);

        List<TripDto> beforeTripList = new ArrayList<>();
        List<TripDto> afterTripList = new ArrayList<>();
        for (TripDto trip : TripList) {
            long tripEndTime = getMilliTime(trip.getEndAt());
            if(now > tripEndTime) {
                beforeTripList.add(trip);
            } else {
                afterTripList.add(trip);
            }
        }
        MyTripListRes res = new MyTripListRes();
        res.setBeforeTripList(beforeTripList);
        res.setAfterTripList(afterTripList);
        return new ResponseWrapper<>(ResponseCode.OK.getCode(), res);
    }

    public ResponseWrapper<LocationRes> getLocationList() {
        List<LocationDto> dto = tripMapper.selLocationList();
        LocationRes res = new LocationRes();
        res.setLocationList(dto);
        return new ResponseWrapper<>(ResponseCode.OK.getCode(), res);
    }

    @Transactional
    public ResponseWrapper<PostTripRes> postTrip(PostTripReq req) {
        long signedUserId = 102L;
        req.setManagerId(signedUserId);
        tripMapper.insTrip(req);
        tripMapper.insTripLocation(req.getTripId(), req.getLocationId());
        PostTripRes res = new PostTripRes();
        res.setTripId(req.getTripId());
        return new ResponseWrapper<PostTripRes>(ResponseCode.OK.getCode(), res);
    }

    public ResponseWrapper<TripDetailRes> getTrip(long tripId) {
        ScheCntAndMemoCntDto scamcdDto = tripMapper.selScheduleCntAndMemoCnt(tripId);
        List<TripDetailDto> tripDetailDto = tripMapper.selScheduleDetail(tripId);
        TripDetailRes res = new TripDetailRes();
        res.setScheduleCnt(scamcdDto.getScheduleCnt());
        res.setMemoCnt(scamcdDto.getMemoCnt());
        res.setDays(tripDetailDto);
        return new ResponseWrapper<>(ResponseCode.OK.getCode(), res);
    }

    @Transactional
    public ResultResponse patchTrip(PatchTripReq req) {
        long tripId = req.getTripId();
        tripMapper.updateTrip(req);

        // 참여 유저 수정
        if(req.getInsUserList() != null && !req.getInsUserList().isEmpty()) {
            tripMapper.insTripUser(tripId, req.getInsUserList());
        }

        if(req.getDelUserList() != null && !req.getDelUserList().isEmpty()) {
            List<Long> scheduleUserIdList = tripMapper.selScheduleUserId(tripId, req.getDelUserList());
            if(scheduleUserIdList != null && !scheduleUserIdList.isEmpty()) {
                tripMapper.delTripMemo(scheduleUserIdList);
                tripMapper.delTripScheMemo(scheduleUserIdList);
            }
            tripMapper.delTripUser(tripId, req.getDelUserList());
        }

        if(req.getInsLocationList() != null && !req.getInsLocationList().isEmpty()) {
            tripMapper.insTripLocation(tripId, req.getInsLocationList());
        }

        if(req.getDelLocationList() != null && !req.getDelLocationList().isEmpty()) {
            tripMapper.delTripLocation(tripId, req.getDelLocationList());
        }

        // 여행 지역 수정
        return ResultResponse.success();
    }

    /*
     * 1. 요청 값인 상품ID 로 DB 에서 상품의 locationId 를 찾기
     * 2. 로그인 유저의 여행 리스트 데이터 DB 에서 가져오기
     * 3. 여행 리스트 중 현재 시간보다 endAt 이 미래라면 미완료 여행으로 파악.
     * 4. 미완료 여행의 총 일차를 계산해서 totalDay 에 담음
     * 5. 미완료 여행 목록 중 상품의 locationId 를 이미 가지고 있는 여행이라면 locateTripList 에 저장, 아니라면 totalTripList 에 저장
     * */
    public ResponseWrapper<IncompleteTripRes> getIncomplete(long strfId) {
        long signedUserId = 101L;
        long strfLocationId = tripMapper.selStrfLocationId(strfId); // 상품의 locationId 찾기
        long now = new Date().getTime();
        List<TripIdMergeDto> dtoList = tripMapper.selIncompleteTripList(signedUserId); // 로그인 유저의 여행 리스트 데이터 DB 에서 가져오기
        if(dtoList == null || dtoList.isEmpty()) { // 일정 없으면 return
            return new ResponseWrapper<>(ResponseCode.OK.getCode(), null);
        }

        IncompleteTripRes res = new IncompleteTripRes();
        List<IncompleteTripDto> locateTripList = new ArrayList<>();
        List<IncompleteTripDto> totalTripList = new ArrayList<>();
        for (TripIdMergeDto groupId : dtoList) {
            IncompleteTripDto temp = null;
            long endAt = getMilliTime(groupId.getIncompleteTripList().get(0).getEndAt());
            if(now > endAt) continue; // 해당 여행의 endAt 이 현재시간보다 과거일시 완료된 여행이므로 넘김
            for (IncompleteTripDto dto : groupId.getIncompleteTripList()) { // tripId 를 그룹으로 잡은 리스트
                if(dto.getLocationId() == strfLocationId) {
                    temp = dto;
                    break;
                } else {
                    temp = dto;
                }
            }
            if(temp == null) break;
            temp.setTotalDay(totalDay(temp.getStartAt(), temp.getEndAt())); // 총 일자 계산해서 totalDay 에 저장
            if(temp.getLocationId() == strfLocationId) { // 상품의 locationId 를 이미 가지고 있는 여행이라면 locateTripList 에 저장, 아니라면 totalTripList 에 저장
                locateTripList.add(temp);
            } else {
                totalTripList.add(temp);
            }
        }
        res.setMatchTripId(locateTripList);
        res.setNoMatchTripId(totalTripList);
        return new ResponseWrapper<>(ResponseCode.OK.getCode(), res);
    }

    /*
    * shcedule 테이블에 상품(일정)을 저장. 이동수단과 거리는 미정
    * trip_location 테이블에 상품의 location 이 존재하지 않으면 저장.
    * */
    public ResultResponse postIncomplete(PostStrfScheduleReq req) {
        long tripId = req.getTripId();
        long strfId = req.getStrfId();
        Long existLocation = tripMapper.existLocation(tripId, strfId);
        long locationId = tripMapper.selStrfLocationId(strfId);

        if(existLocation == null) {
            tripMapper.insTripLocation(tripId, List.of(locationId));
        }
        tripMapper.insScheMemo(req);
        tripMapper.insScheduleStrf(req);
        return ResultResponse.success();
    }

    public ResponseWrapper<List<PubTransPathVo>> getTransPort() {
        String startLat = "35.858798";
        String startLng = "128.523111";
        String endLat = "35.865417";
        String endLng = "128.593601";
        String enKey = URLEncoder.encode(odsayApiConst.getParamApiKeyValue(), StandardCharsets.UTF_8);
        String urlPath = odsayApiConst.getBaseUrl()
                + odsayApiConst.getSearchPubTransPathUrl()
                + "?" + odsayApiConst.getParamApiKeyName() + "=" + enKey
                + "&" + odsayApiConst.getParamStartLatName() + "=" + startLat
                + "&" + odsayApiConst.getParamStartLngName() + "=" + startLng
                + "&" + odsayApiConst.getParamEndLatName() + "=" + endLat
                + "&" + odsayApiConst.getParamEndLngName() + "=" + endLng;


        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add(odsayApiConst.getParamApiKeyName(), odsayApiConst.getParamApiKeyValue());
        formData.add(odsayApiConst.getParamStartLatName(), startLat);
        formData.add(odsayApiConst.getParamStartLngName(), startLng);
        formData.add(odsayApiConst.getParamEndLatName(), endLat);
        formData.add(odsayApiConst.getParamEndLngName(), endLng);

        String json = webClient.post()
                .uri(urlPath)
                .body(BodyInserters.fromFormData(formData))
                .retrieve() //통신 시도
                .bodyToMono(String.class) // 결과물을 String변환
                .block(); //비동기 > 동기
        log.info("json = {}", json);

        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            List<PubTransPathVo> res =  objectMapper.convertValue(jsonNode.at("/result/path")
                    , new TypeReference<>() {});
            log.info("res = {}", res);
            return new ResponseWrapper<>(ResponseCode.OK.getCode(), res);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private long getMilliTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        try {
            return sdf.parse(time).getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private long totalDay(String startAt, String endAt) {

        LocalDate startDate = LocalDate.of(
                Integer.parseInt(startAt.substring(0, 4)),
                Integer.parseInt(startAt.substring(5, 7)),
                Integer.parseInt(startAt.substring(8, 10)));

        LocalDate endDate = LocalDate.of(
                Integer.parseInt(endAt.substring(0, 4)),
                Integer.parseInt(endAt.substring(5, 7)),
                Integer.parseInt(endAt.substring(8, 10)));

        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }
}
