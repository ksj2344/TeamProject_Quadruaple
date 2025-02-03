package com.green.project_quadruaple.booking;

import com.green.project_quadruaple.booking.model.*;
import com.green.project_quadruaple.booking.model.dto.BookingInsDto;
import com.green.project_quadruaple.booking.model.dto.KakaoApproveDto;
import com.green.project_quadruaple.booking.model.dto.KakaoReadyDto;
import com.green.project_quadruaple.common.config.enumdata.ResponseCode;
import com.green.project_quadruaple.common.model.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
public class BookingService {

    private final BookingMapper bookingMapper;
    private final String affiliateCode;
    private final String secretKey;
    private final String payUrl;

    public static final Map<Long, KakaoReadyDto> kakaoTidSession = new HashMap<>();

    public BookingService(BookingMapper bookingMapper,
                          @Value("${kakao-api-const.affiliate-code}") String affiliateCode,
                          @Value("${kakao-api-const.secret-key}") String secretKey,
                          @Value("${kakao-api-const.url}") String payUrl) {
        this.bookingMapper = bookingMapper;
        this.affiliateCode = affiliateCode;
        this.secretKey = secretKey;
        this.payUrl = payUrl;
    }

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
    public ResponseWrapper<String> postBooking(BookingPostReq req) {
        Long userId = 101L;

        Long receiveId = bookingMapper.selExistUserCoupon(userId, req.getCouponId());
        if(receiveId == null) {
            return new ResponseWrapper<>(ResponseCode.BAD_REQUEST.getCode(), "쿠폰 없음");
        }
        req.setReceiveId(receiveId);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", secretKey);
        headers.add("Content-Type", "application/json");

        HashMap<String, String> params = new HashMap<>();

        LocalDateTime localDateTime = LocalDateTime.now();
        String orderNo = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + (int)(Math.random()*1000);
        String quantity = String.valueOf(req.getOrderList().get(0).getQuantity());
        String totalAmount = String.valueOf(req.getActualPaid());
        String taxFreeAmount = String.valueOf((req.getActualPaid()/10));

        params.put("cid", affiliateCode); // 가맹점 코드 - 테스트용
        params.put("partner_order_id", orderNo); // 주문 번호
        params.put("partner_user_id", String.valueOf(userId)); // 회원 아이디
        params.put("item_name", "테스트 상품1"); // 상품 명
        params.put("quantity", quantity); // 상품 수량
        params.put("total_amount", totalAmount); // 상품 가격
        params.put("tax_free_amount", taxFreeAmount); // 상품 비과세 금액
        params.put("approval_url", "http://localhost:8080/api/booking/pay-approve"); // 성공시 url
        params.put("cancel_url", "http://localhost:8080/api/booking/kakaoPayCancle"); // 실패시 url
        params.put("fail_url", "http://localhost:8080/api/booking/kakaoPayFail");

        HttpEntity<HashMap<String, String>> body = new HttpEntity<>(params, headers);

        try {
            KakaoReadyDto kakaoReadyDto = restTemplate.postForObject(new URI(payUrl + "/online/v1/payment/ready"), body, KakaoReadyDto.class);
            log.info("kakaoDto = {}", kakaoReadyDto);
            if(kakaoReadyDto != null) {
                kakaoReadyDto.setPartnerOrderId(orderNo);
                kakaoReadyDto.setPartnerUserId(String.valueOf(userId));
                kakaoReadyDto.setBookingPostReq(req);
                kakaoTidSession.put(userId, kakaoReadyDto);
                OrderThread orderThread = new OrderThread(userId);
                new Thread(orderThread); // 3분 대기후 주문 세션 삭제
                return new ResponseWrapper<>(ResponseCode.OK.getCode(), kakaoReadyDto.getNextRedirectPcUrl());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseWrapper<>(ResponseCode.OK.getCode(), null);
    }

    public String approve(String pgToken) {
        Long userId = 101L;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", secretKey);
        headers.add("Content-Type", "application/json");

        HashMap<String, String> params = new HashMap<>();
        KakaoReadyDto session = kakaoTidSession.get(userId);

        params.put("cid", affiliateCode); // 가맹점 코드 - 테스트용
        params.put("tid", session.getTid()); // 결제 고유 번호, 준비단계 응답에서 가져옴
        params.put("partner_order_id", session.getPartnerOrderId()); // 주문 번호
        params.put("partner_user_id", String.valueOf(userId)); // 회원 아이디
        params.put("pg_token", pgToken); // 준비 단계에서 리다이렉트떄 받은 param 값

        HttpEntity<HashMap<String, String>> body = new HttpEntity<>(params, headers);

        try {
            BookingPostReq bookingPostReq = session.getBookingPostReq();
            bookingPostReq.setUserId(userId);
            bookingPostReq.setTid(session.getTid());
            bookingMapper.insBooking(bookingPostReq);
            bookingMapper.insUsedCoupon(bookingPostReq.getReceiveId(), bookingPostReq.getBookingId());
            KakaoApproveDto approveDto = restTemplate.postForObject(new URI(payUrl + "/online/v1/payment/approve"), body, KakaoApproveDto.class);
            log.info("approveDto = {}", approveDto);
            if(approveDto == null) {
                throw new RuntimeException();
            }
            return "complete";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
