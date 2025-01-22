package com.green.project_quadruaple.trip;

import com.green.project_quadruaple.common.model.ResultResponse;
import com.green.project_quadruaple.trip.model.TripDto;
import com.green.project_quadruaple.trip.model.res.MyTripListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripMapper tripMapper;

    public String getMyTripList() {
        int before = 0;
        int after = 1;
        String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        List<TripDto> beforeTripList = tripMapper.getTripList(now, 101L, before);
        List<TripDto> afterTripList = tripMapper.getTripList(now, 101L, after);
        MyTripListRes res = new MyTripListRes();
        res.setBeforeTripList(beforeTripList);
        res.setAfterTripList(afterTripList);
        ResultResponse resultResponse = new ResultResponse();
        return null;
    }
}
