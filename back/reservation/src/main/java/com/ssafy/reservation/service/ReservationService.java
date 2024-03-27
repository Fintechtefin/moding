package com.ssafy.reservation.service;

import static com.ssafy.reservation.exception.global.CustomExceptionStatus.NOT_FOUND_RSERVATION_ID;

import com.ssafy.reservation.controller.ReservationClient;
import com.ssafy.reservation.domain.Reservation;
import com.ssafy.reservation.dto.request.MakeReservationRequest;
import com.ssafy.reservation.exception.BadRequestException;
import com.ssafy.reservation.exception.global.CustomExceptionStatus;
import com.ssafy.reservation.repository.ReservationRepository;
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
