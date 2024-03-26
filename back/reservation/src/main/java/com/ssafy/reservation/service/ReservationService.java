package com.ssafy.reservation.service;

import static com.ssafy.reservation.exception.global.CustomExceptionStatus.NOT_FOUND_RSERVATION_ID;

import com.ssafy.reservation.domain.Reservation;
import com.ssafy.reservation.exception.BadRequestException;
import com.ssafy.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

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
