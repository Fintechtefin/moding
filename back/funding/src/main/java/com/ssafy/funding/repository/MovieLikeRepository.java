package com.ssafy.funding.repository;

import com.ssafy.funding.domain.FundingStatus;
import com.ssafy.funding.domain.MovieLike;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MovieLikeRepository extends CrudRepository<MovieLike, Integer> {
    long countByMovieId(int movieId);

    MovieLike findByMovieIdAndUserId(int movieId, int userId);

    @Query(
            value =
                    " select movie.movie_id movieId, poster, title, status, (select count(*) from movie_like where movie.movie_id=movie_like.movie_id) likeCnt"
                            + "  from movie join movie_like on movie.movie_id=movie_like.movie_id where movie_like.user_id=:userId",
            nativeQuery = true)
    List<UserLikeMovieResponse> getMyLikeList(int userId);

    boolean existsByUserIdAndMovieId(int userId, int movieId);

    public interface UserLikeMovieResponse {
        int getMovieId();

        String getTitle();

        String getPoster();

        int getLikeCnt();

        FundingStatus getStatus();
    }
}
