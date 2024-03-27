package com.ssafy.funding.domain;

import javax.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "movie_funding")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MovieFunding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_funding_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "time")
    private int time;

    @Column(name = "location")
    private String location;
}
