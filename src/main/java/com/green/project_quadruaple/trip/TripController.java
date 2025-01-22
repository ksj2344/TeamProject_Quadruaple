package com.green.project_quadruaple.trip;

import com.green.project_quadruaple.common.model.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @GetMapping("/trip")
    public String getTripList() {
        tripService.getMyTripList();
        log.info("get trip list");
        return "OK";
    }
}
