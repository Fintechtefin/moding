package com.ssafy.funding.repository;

import com.ssafy.funding.domain.Movie;
import com.ssafy.funding.dto.response.MovieDetailResponse;
import com.ssafy.funding.dto.response.MovieSummaryResponse;

import java.io.Serializable;
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

    @Query(
            "SELECT new com.ssafy.funding.dto.response.MovieSummaryResponse(m.id, m.title, m.status, m.releaseAt, m.runningTime, m.age , m.actors, m.plot, m.poster, (SELECT COUNT(mf) FROM MovieFunding mf WHERE mf.movie.id = m.id)) FROM Movie m "
                    + "WHERE m.id = :movieId")
    Optional<MovieSummaryResponse> getMovieDetailById(@Param("movieId") int movieId);

    @Query(
            "SELECT g.type FROM Genre g WHERE g.id IN (SELECT mg.genre.id FROM MovieGenre mg WHERE mg.movie.id = :movieId)")
    Optional<List<String>> getGenreById(@Param("movieId") int movieId);

    @Query(
            "SELECT count(fh) FROM FundingHistory fh WHERE fh.funding.movie.id=:movieId and fh.fundingFinalResult=true")
    int getSuccessCountById(@Param("movieId") int movieId);

    @Query(
            value ="select movie.movie_id movieId, movie.status, movie.poster, movie.title from movie " +
                    "join (" +
                    "select movie_id, genre_id from movie_genre) movie_genre " +
                    "on movie.movie_id=movie_genre.movie_id " +
                    "join (" +
                    "select genre_id, parent_genre_id from genre) genre " +
                    "on movie_genre.genre_id=genre.genre_id " +
                    "where genre.parent_genre_id=:genreId group by movie.movie_id "+
                    "order by title desc "+
                    "limit :page,21 ",
            nativeQuery = true)
    List<MovieGenreListResponse> getMovieByGenreTitleDesc(int genreId, int page);

    @Query(
            value ="select movie.movie_id movieId, movie.status, movie.poster, movie.title from movie " +
                    "join (" +
                    "select movie_id, genre_id from movie_genre) movie_genre " +
                    "on movie.movie_id=movie_genre.movie_id " +
                    "join (" +
                    "select genre_id, parent_genre_id from genre) genre " +
                    "on movie_genre.genre_id=genre.genre_id " +
                    "where genre.parent_genre_id=:genreId group by movie.movie_id "+
                    "order by title asc "+
                    "limit :page,21 ",
            nativeQuery = true)
    List<MovieGenreListResponse> getMovieByGenreTitleAsc(int genreId, int page);

    @Query(
            value ="select movie.movie_id movieId, movie.status, movie.poster, movie.title, (select count(*) from movie_like where movie_id=movie.movie_id) as likeCnt from movie " +
                    "join (" +
                    "select movie_id, genre_id from movie_genre) movie_genre " +
                    "on movie.movie_id=movie_genre.movie_id " +
                    "join (" +
                    "select genre_id, parent_genre_id from genre) genre " +
                    "on movie_genre.genre_id=genre.genre_id " +
                    "where genre.parent_genre_id=:genreId group by movie.movie_id "+
                    "order by likeCnt asc "+
                    "limit :page,21 ",
            nativeQuery = true)
    List<MovieGenreListResponse> getMovieByGenreLikeAsc(int genreId, int page);

    @Query(
            value ="select movie.movie_id movieId, movie.status, movie.poster, movie.title, (select count(*) from movie_like where movie_id=movie.movie_id) as likeCnt from movie " +
                    "join (" +
                    "select movie_id, genre_id from movie_genre) movie_genre " +
                    "on movie.movie_id=movie_genre.movie_id " +
                    "join (" +
                    "select genre_id, parent_genre_id from genre) genre " +
                    "on movie_genre.genre_id=genre.genre_id " +
                    "where genre.parent_genre_id=:genreId group by movie.movie_id "+
                    "order by likeCnt desc "+
                    "limit :page,21 ",
            nativeQuery = true)
    List<MovieGenreListResponse> getMovieByGenreLikeDesc(int genreId, int page);



    public interface MovieGenreListResponse extends Serializable {
        int getMovieId();

        FundingStatus getStatus();

        String getPoster();

    }
}
