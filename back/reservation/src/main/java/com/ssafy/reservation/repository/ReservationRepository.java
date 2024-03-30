package com.ssafy.reservation.repository;

import com.ssafy.reservation.domain.Reservation;
import com.ssafy.reservation.dto.ListSeat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    //    List<ReservationRepository.ReservationInfo> ReservationInfo = ;

    public interface ReservationInfo {
        int getFundingId();
    }

    public interface ReservationIdInterface {
        int getFundingId();
    }

    Reservation findByFundingIdAndSeats(final Integer fundingId, final ListSeat seats);

    List<ReservationInfo> findByUserIdAndStatus(final Integer userId, final Integer status);

    ReservationIdInterface findByUserIdAndFundingIdAndStatus(
            final Integer userId, final Integer fundingId, final Integer status);
}
