package com.green.project_quadruaple.booking;

import com.green.project_quadruaple.booking.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookingMapper {
    List<BookingListGetRes> getBooking(BookingListGetReq req);

    BookingPostRes postBooking(BookingPostReq req);
}
