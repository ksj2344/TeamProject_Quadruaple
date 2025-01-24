package com.green.project_quadruaple.trip;

import com.green.project_quadruaple.common.model.ResponseWrapper;
import com.green.project_quadruaple.common.model.ResultResponse;
import com.green.project_quadruaple.trip.model.req.PatchTripReq;
import com.green.project_quadruaple.trip.model.req.PostTripReq;
import com.green.project_quadruaple.trip.model.res.LocationRes;
import com.green.project_quadruaple.trip.model.res.MyTripListRes;
import com.green.project_quadruaple.trip.model.res.PostTripRes;
import com.green.project_quadruaple.trip.model.res.TripDetailRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @GetMapping("/trip-list")
    public ResponseWrapper<MyTripListRes> getTripList() {
        return tripService.getMyTripList();
    }

    @PostMapping("/trip")
    public ResponseWrapper<PostTripRes> postTrip(@RequestBody PostTripReq req) {
        return tripService.postTrip(req);
    }

    @GetMapping("/trip/location")
    public ResponseWrapper<LocationRes> getLocationList(@RequestParam("location_id") long locationId) {
        return tripService.getLocationList(locationId);
    }

    @GetMapping("/trip")
    public ResponseWrapper<TripDetailRes> getTrip(@RequestParam("trip_id") long tripId) {
        return tripService.getTrip(tripId);
    }

//    @PatchMapping("trip")
//    public ResponseWrapper patchTrip(PatchTripReq req) {
//        return tripService.patchTrip(req);
//    }
}
