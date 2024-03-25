package com.ssafy.funding.repository;

import com.ssafy.funding.domain.Funding;
import com.ssafy.funding.domain.FundingStatus;

import java.util.List;
import java.util.Optional;

import com.ssafy.funding.domain.Movie;
import org.springframework.data.repository.CrudRepository;

public interface FundingRepository extends CrudRepository<Funding, Integer> {
    Optional<Funding> findByIdAndStatus(Integer fundingId, FundingStatus status);

}
