package com.ssafy.funding.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "movie")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Integer id;

    @Column(length = 50)
    private String title;

    @Column(length = 30)
    private String age;

    @Column(length = 200)
    private String poster;

    @Column(name="release_at",length=10)
    private String releaseAt;

    @Column(length=100)
    private String actors;

    @Column(length=20)
    private String status;

    @Column(name="running_time")
    private int runningTime;

    @Column(length=5000)
    private String plot;

    @OneToMany(mappedBy = "movie")
    private List<MovieGenre> movieGenreList=new ArrayList<>();


}
