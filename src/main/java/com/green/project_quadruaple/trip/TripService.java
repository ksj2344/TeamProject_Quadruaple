package com.green.project_quadruaple.trip;

import com.green.project_quadruaple.common.config.security.AuthenticationFacade;
import com.green.project_quadruaple.common.model.ResultResponse;
import com.green.project_quadruaple.trip.model.LocationDto;
import com.green.project_quadruaple.trip.model.TripDto;
import com.green.project_quadruaple.trip.model.req.PostTripReq;
import com.green.project_quadruaple.trip.model.res.LocationRes;
import com.green.project_quadruaple.trip.model.res.MyTripListRes;
import com.green.project_quadruaple.trip.model.res.PostTripRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripMapper tripMapper;

    public ResultResponse getMyTripList() {
//        long signedUserId = AuthenticationFacade.getSignedUserId();
        long signedUserId = 101L;
        int before = 0;
        int after = 1;
        String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        List<TripDto> beforeTripList = tripMapper.getTripList(now, signedUserId, before);
        List<TripDto> afterTripList = tripMapper.getTripList(now, signedUserId, after);
        MyTripListRes res = new MyTripListRes();
        res.setBeforeTripList(beforeTripList);
        res.setAfterTripList(afterTripList);
        return res;
    }

    public ResultResponse getLocationList(long locationId) {
        List<LocationDto> dto = tripMapper.selLocationList(locationId);
        LocationRes res = new LocationRes();
        res.setLocationList(dto);
        return res;
    }

    @Transactional
    public ResultResponse postTrip(PostTripReq req) {
        long signedUserId = 102L;
        req.setManagerId(signedUserId);
        tripMapper.insTrip(req);
        tripMapper.insTripLocation(req.getTripId(), req.getLocationId());
        PostTripRes res = new PostTripRes();
        res.setTripId(req.getTripId());
        return res;
    }
}
