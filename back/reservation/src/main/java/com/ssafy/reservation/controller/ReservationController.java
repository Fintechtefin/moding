package com.ssafy.reservation.controller;

import com.ssafy.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    public final ReservationService reservationService;

    @Operation(summary = "좌석 예매를 취소합니다.")
    @PutMapping("/cancel/{reservationId}")
    public ResponseEntity<?> cancelReservation(@PathVariable final Integer reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.noContent().build();
    }
}
