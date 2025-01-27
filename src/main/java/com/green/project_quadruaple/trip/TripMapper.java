package com.green.project_quadruaple.trip;

import com.green.project_quadruaple.trip.model.dto.*;
import com.green.project_quadruaple.trip.model.req.*;
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

    // postSchedule
    Long existLocation(long tripId, long strfId);
    void insScheMemo(PostScheduleReq req);
    void insSchedule(PostScheduleReq req);

    // deleteSchedule
    long selScheduleByScheduleId(long scheduleId);
    ScheduleDto selScheduleAndScheMemoByScheduleId(long scheduleId, long tripId);
    void updateSeqScheMemo(long tripId, int seq);
    void updateSchedule(boolean isNotFirst, long nextScheduleId, int distance, int duration, int pathType);
    void deleteSchedule(long scheduleId);
    void deleteScheMemo(long scheduleId);

    // deleteTripUser
    Long selTripById(long tripId);
    void disableTripUser(long tripId, long userId);
    StrfLatAndLngDto selStrfLatAndLng(long strfId);
}
