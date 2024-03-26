package com.ssafy.funding.repository;

import com.ssafy.funding.domain.Movie;
import com.ssafy.funding.dto.DetailMovie;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    List<Movie> findByTitleContainingOrActorsContaining(String title, String actors);

    @Query(
            "SELECT new com.ssafy.funding.dto.DetailMovie(m.id, m.title, m.poster, m.status, (SELECT COUNT(ml) FROM MovieLike ml WHERE ml.movieId = m.id)) FROM Movie m "
                    + "JOIN m.movieGenreList mg "
                    + "JOIN mg.genre g "
                    + "WHERE g.parentGenreId = (SELECT g2.id FROM Genre g2 WHERE g2.type = :genre)")
    List<DetailMovie> findMoviesByParentGenreId(@Param("genre") String genre);
}
