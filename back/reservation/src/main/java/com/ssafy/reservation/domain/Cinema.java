package com.ssafy.reservation.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cinema")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_id")
    private Integer id;

    private String name;

    private String region;

    private String groupGu;

    private String detailGu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_operator_id")
    private CinemaOperator cinemaOperator;
}
