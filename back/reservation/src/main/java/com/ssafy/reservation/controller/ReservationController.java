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

    @Operation(summary = "해당 펀딩에 예약된 좌석을 조회합니다.")
    @GetMapping("/get/seat/{fundingId}")
    public ResponseEntity<?> getSeatList(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable("fundingId") int fundingId) {
        int userId = reservationService.getCurrentUserId(accessToken);

        return ResponseEntity.ok().body(reservationService.getSeatList(userId, fundingId));
    }

    @Operation(summary = "좌석 예매를 합니다. 예매 전, 요청한 좌석이 빈좌석일경우에만 예매 가능합니다. 예매 완료 후 예매 번호를 반환합니다.")
    //    @RequestMapping(value = "/make", method = RequestMethod.POST)
    @PostMapping("/make")
    public ResponseEntity<?> makeReservation(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody MakeReservationRequest makeReservationRequest) {
        int userId = reservationService.getCurrentUserId(accessToken);

        reservationService.checkReservation(makeReservationRequest.getFundingId(), userId);

        int reservationId = reservationService.checkSeat(makeReservationRequest, userId);
        //        int reservationId = reservationService.makeReservation(makeReservationRequest,
        // userId);

        return ResponseEntity.ok().body(reservationId);
    }

    @Operation(summary = "좌석 예매를 취소합니다.")
    @PutMapping("/cancel/{reservationId}")
    public ResponseEntity<?> cancelReservation(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable final Integer reservationId) {
        int userId = reservationService.getCurrentUserId(accessToken);

        reservationService.cancelReservation(reservationId, userId);
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
        int userId = reservationService.getCurrentUserId(accessToken);
        TicketInfoResponse ticketInfoResponse =
                reservationService.getRecentTicket(accessToken, userId);
        return ResponseEntity.ok().body(ticketInfoResponse);
    }
}
