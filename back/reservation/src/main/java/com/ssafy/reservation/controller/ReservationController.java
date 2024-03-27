package com.ssafy.reservation.controller;

import com.ssafy.reservation.dto.request.MakeReservationRequest;
import com.ssafy.reservation.dto.response.CreateTicketResponse;
import com.ssafy.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
// @RequestMapping("/reservation")
public class ReservationController {
    public final ReservationService reservationService;

    @Operation(summary = "좌석 예매를 합니다. 예매 전, 요청한 좌석이 빈좌석일경우에만 예매 가능합니다. 예매 완료후에는 티켓이 발급됩니다.")
    //    @RequestMapping(value = "/make", method = RequestMethod.POST)
    @PostMapping("/make")
    public ResponseEntity<?> makeReservation(
            @RequestBody MakeReservationRequest makeReservationRequest) {
        reservationService.checkPaymentUser(
                makeReservationRequest.getFundingId(), makeReservationRequest.getUserId());

        // 좌석 확인
        reservationService.checkSeat(makeReservationRequest);

        // 좌석 예매 저장
        reservationService.makeReservation(makeReservationRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "좌석 예매를 취소합니다.")
    @PutMapping("/cancel/{reservationId}")
    public ResponseEntity<?> cancelReservation(@PathVariable final Integer reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "티켓을 발급합니다.")
    @GetMapping("/create/{reservationId}")
    public ResponseEntity<?> createTicket(@PathVariable final Integer reservationId) {
        CreateTicketResponse createTicketResponse = reservationService.createTicket(reservationId);
        return ResponseEntity.ok().body(createTicketResponse);
    }
}
