package com.ssafy.funding.repository;

import com.ssafy.funding.domain.Movie;
import com.ssafy.funding.dto.response.MovieDescResponse;
import com.ssafy.funding.dto.response.MovieDetailResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

    List<MovieDetailResponse> findByTitleContainingOrActorsContaining(String title, String actors);

    @Query(
            "SELECT new com.ssafy.funding.dto.response.MovieDetailResponse(m.id, m.title, m.poster, m.status, (SELECT COUNT(ml) FROM MovieLike ml WHERE ml.movieId = m.id)) FROM Movie m "
                    + "JOIN m.movieGenreList mg "
                    + "JOIN mg.genre g "
                    + "WHERE g.parentGenreId = :genre")
    List<MovieDetailResponse> findMoviesByParentGenreId(@Param("genre") int genre);

//    @Query(
//            "SELECT new com.ssafy.funding.dto.response.MovieDescResponse()"
//    )
//    Optional<MovieDescResponse> findMovieDescById(@Param("movieId") int movieId);
}
