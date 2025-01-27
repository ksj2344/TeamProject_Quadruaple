package com.green.project_quadruaple.booking;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("booking")
@RequiredArgsConstructor
@Tag(name = "예약")
public class BookingController {
}
