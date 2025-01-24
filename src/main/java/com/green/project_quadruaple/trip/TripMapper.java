package com.green.project_quadruaple.trip;

import com.green.project_quadruaple.trip.model.dto.LocationDto;
import com.green.project_quadruaple.trip.model.dto.ScheCntAndMemoCntDto;
import com.green.project_quadruaple.trip.model.dto.TripDetailDto;
import com.green.project_quadruaple.trip.model.dto.TripDto;
import com.green.project_quadruaple.trip.model.req.PatchTripReq;
import com.green.project_quadruaple.trip.model.req.PostTripReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TripMapper {

    List<TripDto> getTripList(String currentAt, long userId);

    List<LocationDto> selLocationList();

    void insTrip(PostTripReq req);

    void insTripLocation(long tripId, List<Long> idList);

    TripDetailDto getTrip(long tripId);

    ScheCntAndMemoCntDto selScheduleCntAndMemoCnt(long tripId);
    List<TripDetailDto> selScheduleDetail(long tripId);

    void updateTrip(PatchTripReq req);
    void insTripUser(long tripId, List<Long> userIdList);

    void delTripMemo(List<Long> scheduleIdList);
    void delTripUser(long tripId, List<Long> userIdList);
    void delTripScheMemo(List<Long> scheduleIdList);

    void delTripLocation(long tripId, List<Long> locationList);

    List<Long> selScheduleUserId(long tripId, List<Long> userIdList);
}
