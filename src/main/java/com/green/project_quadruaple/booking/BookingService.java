package com.green.project_quadruaple.booking;

import com.green.project_quadruaple.booking.model.*;
import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingMapper bookingMapper;

    public ResponseWrapper<BookingListResponse> getBooking(BookingListGetReq req) {
        List<BookingListGetRes> bookingList = bookingMapper.getBooking(req);

        List<BookingListGetRes> beforeList = new ArrayList<>();
        List<BookingListGetRes> afterList = new ArrayList<>();

        for (BookingListGetRes booking : bookingList) {
            if ("progress".equals(booking.getBookingStatus())) {
                beforeList.add(booking);
            } else {
                afterList.add(booking);
            }
        }

        BookingListResponse res = new BookingListResponse();
        res.setBeforeList(beforeList);
        res.setAfterList(afterList);

        return new ResponseWrapper<>(ResponseCode.OK.getCode(), res);
    }




    // 예약 변경 -> 결제 , 취소 내역 업데이트 필요
    // check in - out 날짜 체크 (일정의 날짜 , check in - out 날짜 겹침x)
    // final_paymaent = 실제 결제 금액 맞는지 비교 필요
    // strf id 가 실제 상품과 맞는지 체크
    @Transactional
    public ResponseWrapper<BookingPostRes> postBooking(BookingPostReq req) {
        bookingMapper.postBooking(req);

        BookingPostRes res = new BookingPostRes();
        res.setBookingId(req.getBookingId());


        return new ResponseWrapper<>(ResponseCode.OK.getCode(), res);
    }


}
