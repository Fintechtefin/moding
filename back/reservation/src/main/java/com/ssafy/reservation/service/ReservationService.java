package com.ssafy.reservation.service;

import static com.ssafy.reservation.exception.global.CustomExceptionStatus.NOT_FOUND_RSERVATION_ID;

import com.ssafy.reservation.controller.ReservationClient;
import com.ssafy.reservation.domain.Reservation;
import com.ssafy.reservation.dto.request.MakeReservationRequest;
import com.ssafy.reservation.dto.response.CreateTicketResponse;
import com.ssafy.reservation.exception.BadRequestException;
import com.ssafy.reservation.exception.global.CustomExceptionStatus;
import com.ssafy.reservation.repository.ReservationRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationClient reservationClient;
    private final Integer completedStatus = 1;

    public void checkPaymentUser(int fundingId, int userId) {

        if (!reservationClient.checkPaymentUser(fundingId, userId)) {
            throw new BadRequestException(CustomExceptionStatus.NOT_PAYMENTS_USER);
        }
    }

    public void checkSeat(MakeReservationRequest makeReservationRequest) {
        Reservation reservation =
                reservationRepository.findByFundingIdAndSeats(
                        makeReservationRequest.getFundingId(), makeReservationRequest.getSeat());

        if (reservation != null) {
            throw new BadRequestException(CustomExceptionStatus.RESERVATION_SEAT);
        }
    }

    // 좌석 예매 내역 db 저장
    @Transactional
    public void makeReservation(MakeReservationRequest makeReservationRequest) {
        Reservation reservation = Reservation.of(makeReservationRequest, completedStatus);
        reservationRepository.save(reservation);
    }

    public CreateTicketResponse createTicket(Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow();
        if (reservation.getStatus() == 0) {
            throw new BadRequestException(CustomExceptionStatus.CANCELED_RSERVATION_ID);
        }
        CreateTicketResponse createTicketResponse =
                new CreateTicketResponse(
                        reservation.getSeats(),
                        "poster.jpg",
                        "15세 이상 관람가",
                        "엘리멘탈",
                        LocalDate.parse("2024-04-05", DateTimeFormatter.ISO_DATE),
                        "12:30",
                        120,
                        1,
                        "광주 롯데시네마 수완점",
                        5);
        return createTicketResponse;
    }

    @Transactional
    public void cancelReservation(final Integer reservationId) {
        final Reservation reservation =
                reservationRepository
                        .findById(reservationId)
                        .orElseThrow(() -> new BadRequestException(NOT_FOUND_RSERVATION_ID));

        reservation.changeStatus();
        reservationRepository.save(reservation);
    }
}
