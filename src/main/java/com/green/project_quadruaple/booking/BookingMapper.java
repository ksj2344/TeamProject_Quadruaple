package com.green.project_quadruaple.booking;

import com.green.project_quadruaple.booking.model.BookingListGetReq;
import com.green.project_quadruaple.booking.model.BookingListGetRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookingMapper {
    BookingListGetRes bookingList (BookingListGetReq req);


}
