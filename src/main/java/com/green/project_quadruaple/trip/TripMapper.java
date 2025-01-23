package com.green.project_quadruaple.trip;

import com.green.project_quadruaple.trip.model.LocationDto;
import com.green.project_quadruaple.trip.model.TripDto;
import com.green.project_quadruaple.trip.model.req.PostTripReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TripMapper {

    List<TripDto> getTripList(String currentAt, long userId, int bOrA);

    List<LocationDto> selLocationList(long locationId);

    void insTrip(PostTripReq req);

    void insTripLocation(Long tripId, List<Long> idList);
}
