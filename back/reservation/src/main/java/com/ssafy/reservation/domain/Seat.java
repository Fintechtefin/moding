package com.ssafy.reservation.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "seat")
// @Table(name = "seat", indexes = @Index(name = "idx_funding_id_and_position", columnList =
// "fundingId, position"))
// @Table(name = "seat", indexes = @Index(name = "idx_funding_id_and_position_and_position_status",
// columnList = "fundingId, position, status"))
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Integer id;

    private String position;

    private Integer fundingId;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public static Seat of(final String position, final int fundingId, Reservation reservation) {
        Seat seat =
                Seat.builder()
                        .position(position)
                        .fundingId(fundingId)
                        .reservation(reservation)
                        .build();
        return seat;
    }
}
