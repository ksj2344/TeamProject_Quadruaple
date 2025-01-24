package com.green.project_quadruaple.trip;

import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.common.model.ResultResponse;
import com.green.project_quadruaple.trip.model.dto.LocationDto;
import com.green.project_quadruaple.trip.model.dto.ScheCntAndMemoCntDto;
import com.green.project_quadruaple.trip.model.dto.TripDetailDto;
import com.green.project_quadruaple.trip.model.dto.TripDto;
import com.green.project_quadruaple.trip.model.req.PatchTripReq;
import com.green.project_quadruaple.trip.model.req.PostTripReq;
import com.green.project_quadruaple.trip.model.res.LocationRes;
import com.green.project_quadruaple.trip.model.res.MyTripListRes;
import com.green.project_quadruaple.trip.model.res.PostTripRes;
import com.green.project_quadruaple.trip.model.res.TripDetailRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripService {

    private final TripMapper tripMapper;

    public ResponseWrapper<MyTripListRes> getMyTripList() {
//        long signedUserId = AuthenticationFacade.getSignedUserId();
        long signedUserId = 101L;
        long now = new Date().getTime();
        List<TripDto> TripList = tripMapper.getTripList(String.valueOf(now), signedUserId);

        List<TripDto> beforeTripList = new ArrayList<>();
        List<TripDto> afterTripList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        for (TripDto trip : TripList) {
            try {
                long tripEndTime = sdf.parse(trip.getEndAt()).getTime();
                if(now > tripEndTime) {
                    beforeTripList.add(trip);
                } else {
                    afterTripList.add(trip);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        MyTripListRes res = new MyTripListRes();
        res.setBeforeTripList(beforeTripList);
        res.setAfterTripList(afterTripList);
        return new ResponseWrapper<>(ResponseCode.OK.getCode(), res);
    }

    public ResponseWrapper<LocationRes> getLocationList(long locationId) {
        List<LocationDto> dto = tripMapper.selLocationList(locationId);
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

//    @Transactional
//    public ResponseWrapper patchTrip(PatchTripReq req) {
//        tripMapper.updateTrip(req);
//    }
}
