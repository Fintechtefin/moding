package com.ssafy.reservation.domain;

import com.ssafy.reservation.dto.ListSeat;
import com.ssafy.reservation.dto.request.MakeReservationRequest;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name = "json", typeClass = JsonType.class)
@Table(name = "reservation")
public class Reservation extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Integer id;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private ListSeat seats;

    private Integer status;

    private Integer userId;

    private Integer fundingId;

    public void changeStatus() {
        this.status = 0;
    }

    public static Reservation of(
            final MakeReservationRequest makeReservationRequest, int userId, int status) {
        Reservation reservation =
                Reservation.builder()
                        .seats(makeReservationRequest.getSeats())
                        .status(status)
                        .userId(userId)
                        .fundingId(makeReservationRequest.getFundingId())
                        .build();
        return reservation;
    }
}
