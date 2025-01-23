package com.green.project_quadruaple.trip;

import com.green.project_quadruaple.common.model.ResultResponse;
import com.green.project_quadruaple.trip.model.req.PostTripReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @GetMapping("/trip")
    public ResultResponse getTripList() {
        ResultResponse res = tripService.getMyTripList();
        return res;
    }

    @PostMapping("/trip")
    public ResultResponse postTrip(@RequestBody PostTripReq req) {
        return tripService.postTrip(req);
    }

    @GetMapping("/trip/location")
    public ResultResponse getLocationList(@RequestParam("location_id") long locationId) {
        return tripService.getLocationList(locationId);
    }
}
