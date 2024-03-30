package com.ssafy.funding.repository;

import com.ssafy.funding.domain.MovieFunding;
import java.util.List;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;

public interface MovieFundingRepository extends CrudRepository<MovieFunding, Integer> {
    Slice<MovieFunding> findByUserId(int userId);

    List<MovieFunding> findByMovieId(int movieId);
}
