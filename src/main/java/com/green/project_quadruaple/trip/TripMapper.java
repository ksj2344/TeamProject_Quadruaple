package com.green.project_quadruaple.trip;

import com.green.project_quadruaple.trip.model.TripDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TripMapper {

    List<TripDto> getTripList(String currentAt, long userId, int bOrA);
}
