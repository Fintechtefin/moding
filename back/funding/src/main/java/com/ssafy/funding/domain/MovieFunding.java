package com.ssafy.funding.domain;

import com.ssafy.funding.dto.request.MovieFundingRequest;
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

    public static MovieFunding of(
            MovieFundingRequest movieFundingRequest, int userId, Movie movie) {
        MovieFunding movieFunding = new MovieFunding();

        movieFunding.movie = movie;
        movieFunding.userId = userId;
        movieFunding.time = movieFundingRequest.getTime();
        movieFunding.location = movieFundingRequest.getLocation();

        return movieFunding;
    }

    public String getMovieTitle() {
        return this.getMovie().getTitle();
    }

    public Integer getIdOfMovie() {
        return this.getMovie().getId();
    }

    public String getMoviePoster() {
        return this.getMovie().getPoster();
    }
}
