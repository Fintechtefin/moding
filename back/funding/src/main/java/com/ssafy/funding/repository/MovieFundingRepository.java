package com.ssafy.funding.repository;

import com.ssafy.funding.domain.MovieFunding;
import org.springframework.data.repository.CrudRepository;

public interface MovieFundingRepository extends CrudRepository<MovieFunding, Integer> {}
