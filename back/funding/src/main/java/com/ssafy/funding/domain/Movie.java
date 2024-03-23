package com.ssafy.funding.domain;

import com.ssafy.funding.domain.document.MovieDocument;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

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

    @Column(name = "release_at", length = 10)
    private String releaseAt;

    @Column(length = 100)
    private String actors;

    @Column(length = 20)
    private String status;

    @Column(name = "running_time")
    private int runningTime;

    @Column(length = 5000)
    private String plot;

    @OneToMany(mappedBy = "movie")
    private List<MovieGenre> movieGenreList = new ArrayList<>();

    public static Movie of(MovieDocument movieDocument) {
        Movie movie = new Movie();

        movie.id = movieDocument.getId();
        movie.poster = movieDocument.getPoster();
        movie.age = movieDocument.getAge();
        movie.title = movieDocument.getTitle();
        movie.status = movieDocument.getStatus();

        return movie;
    }
}
