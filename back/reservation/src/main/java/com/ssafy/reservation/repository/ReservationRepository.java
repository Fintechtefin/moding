package com.ssafy.reservation.repository;

import com.ssafy.reservation.domain.Reservation;
import com.ssafy.reservation.dto.ListSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Reservation findByFundingIdAndSeats(final Integer fundingId, final ListSeat seats);
}
