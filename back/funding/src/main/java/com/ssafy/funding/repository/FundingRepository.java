package com.ssafy.funding.repository;

import com.ssafy.funding.domain.Funding;
import com.ssafy.funding.domain.FundingStatus;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface FundingRepository extends CrudRepository<Funding, Integer> {
    Optional<Funding> findByIdAndStatus(Integer fundingId, FundingStatus status);
}
