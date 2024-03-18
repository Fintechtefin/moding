package com.ssafy.reservation.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_seat_id")
    private Long Id;

    private String line;

    private Integer columns;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_info_id")
    private CinemaInfo cinemaInfo;
}
