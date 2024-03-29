package com.ssafy.funding.repository;

import com.ssafy.funding.domain.MovieLike;
import org.springframework.data.repository.CrudRepository;

public interface MovieLikeRepository extends CrudRepository<MovieLike, Integer> {
    long countByMovieId(int movieId);

    MovieLike findByMovieIdAndUserId(int movieId, int userId);
}
