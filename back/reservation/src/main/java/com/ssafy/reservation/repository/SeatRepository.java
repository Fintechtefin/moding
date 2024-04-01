package com.ssafy.reservation.repository;

import com.ssafy.reservation.domain.Seat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    public interface SeatPositionInfo {
        String getPosition();
    }

    Seat findByFundingIdAndPosition(int fundingId, String position);

    List<SeatPositionInfo> findByReservationIdAndFundingId(int reservationId, int fundingId);
}
