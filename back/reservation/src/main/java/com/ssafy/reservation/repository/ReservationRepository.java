package com.ssafy.reservation.repository;

import com.ssafy.reservation.domain.Reservation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    public interface ReservationInfo {
        int getFundingId();
    }

    public interface ReservationIdInterface {
        int getFundingId();
    }

    List<Reservation> findListByFundingIdAndStatus(final int fundingId, final int status);

    List<ReservationInfo> findByUserIdAndStatus(final Integer userId, final Integer status);

    ReservationIdInterface findByUserIdAndFundingIdAndStatus(
            final Integer userId, final Integer fundingId, final Integer status);

    Reservation findByIdAndUserIdAndStatus(
            final int reservationId, final int userId, final int status);
}
