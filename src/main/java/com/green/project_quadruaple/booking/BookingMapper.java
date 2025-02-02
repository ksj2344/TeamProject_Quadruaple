package com.green.project_quadruaple.booking;

import com.green.project_quadruaple.booking.model.*;
import com.green.project_quadruaple.booking.model.dto.MenuIdAndQuantityDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookingMapper {
    List<BookingListGetRes> getBooking(BookingListGetReq req);

    BookingPostRes postBooking(BookingPostReq req);

    Long selExistUserCoupon(Long userId, Long couponId);

    void insBooking(Integer actualPaid, String checkIn, String checkOut, Long userId, List<MenuIdAndQuantityDto> orderList);
    void insUsedCoupon(Long receiveId, Long bookingId);
}
