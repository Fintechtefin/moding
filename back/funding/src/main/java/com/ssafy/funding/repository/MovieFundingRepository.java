package com.ssafy.funding.repository;

import com.ssafy.funding.domain.MovieFunding;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieFundingRepository extends CrudRepository<MovieFunding, Integer> {
    Slice<MovieFunding> findByUserId(int userId);

    List<MovieFunding> findByMovieId(int movieId);
}
