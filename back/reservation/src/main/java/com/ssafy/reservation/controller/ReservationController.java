package com.ssafy.reservation.controller;

import com.ssafy.reservation.dto.request.MakeReservationRequest;
import com.ssafy.reservation.dto.response.TicketInfoResponse;
import com.ssafy.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// @CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
// @RequestMapping("/reservation")
public class ReservationController {
    public final ReservationService reservationService;

    @Operation(summary = "좌석 예매를 합니다. 예매 전, 요청한 좌석이 빈좌석일경우에만 예매 가능합니다. 예매 완료후에는 티켓이 발급됩니다.")
    //    @RequestMapping(value = "/make", method = RequestMethod.POST)
    @PostMapping("/make")
    public ResponseEntity<?> makeReservation(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody MakeReservationRequest makeReservationRequest) {
        int userId =
                reservationService.checkReservation(
                        accessToken, makeReservationRequest.getFundingId());

        reservationService.checkPaymentUser(
                makeReservationRequest.getFundingId(), makeReservationRequest.getUserId());

        reservationService.checkSeat(makeReservationRequest);
        Integer reservationId = reservationService.makeReservation(makeReservationRequest, userId);

        return ResponseEntity.ok().body(reservationId);
    }

    @Operation(summary = "좌석 예매를 취소합니다.")
    @PutMapping("/cancel/{reservationId}")
    public ResponseEntity<?> cancelReservation(@PathVariable final Integer reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "예매된 티켓을 조회합니다.")
    @GetMapping("/get/{reservationId}")
    public ResponseEntity<?> getTicket(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable final int reservationId) {
        TicketInfoResponse ticketInfoResponse =
                reservationService.getTicket(accessToken, reservationId);
        return ResponseEntity.ok().body(ticketInfoResponse);
    }

    @Operation(summary = "예매 내예매 내역 중 곧 상영될 티켓을 조회합니다.")
    @GetMapping("/recent")
    public ResponseEntity<?> getRecentTicket(@RequestHeader("Authorization") String accessToken) {
        TicketInfoResponse ticketInfoResponse = reservationService.getRecentTicket(accessToken);
        return ResponseEntity.ok().body(ticketInfoResponse);
    }
}
