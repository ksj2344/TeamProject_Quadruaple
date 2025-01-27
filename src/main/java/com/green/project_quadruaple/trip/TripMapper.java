package com.green.project_quadruaple.trip;

import com.green.project_quadruaple.trip.model.dto.*;
import com.green.project_quadruaple.trip.model.req.PatchTripReq;
import com.green.project_quadruaple.trip.model.req.PostStrfScheduleReq;
import com.green.project_quadruaple.trip.model.req.PostTripReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TripMapper {

    List<TripDto> getTripList(long userId);

    List<LocationDto> selLocationList();

    void insTrip(PostTripReq req);

    void insTripLocation(long tripId, List<Long> idList);

    TripDetailDto getTrip(long tripId);

    ScheCntAndMemoCntDto selScheduleCntAndMemoCnt(long tripId);
    List<TripDetailDto> selScheduleDetail(long tripId);

    // patchTrip
    void updateTrip(PatchTripReq req);
    void insTripUser(long tripId, List<Long> userIdList);
    void delTripMemo(List<Long> scheduleIdList);
    void delTripUser(long tripId, List<Long> userIdList);
    void delTripScheMemo(List<Long> scheduleIdList);
    void delTripLocation(long tripId, List<Long> locationList);


    // getIncomplete
    List<Long> selScheduleUserId(long tripId, List<Long> userIdList);
    long selStrfLocationId(long strfId);
    List<TripIdMergeDto> selIncompleteTripList(long userId);

    // postIncomplete
    Long existLocation(long tripId, long strfId);
    void insScheMemo(PostStrfScheduleReq req);
    void insScheduleStrf(PostStrfScheduleReq req);

}
