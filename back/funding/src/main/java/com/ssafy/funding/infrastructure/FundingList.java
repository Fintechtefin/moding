package com.ssafy.funding.infrastructure;

import com.ssafy.funding.repository.FundingRepository;

public interface FundingList {
    Object getFundingList(FundingRepository fundingRepository);

    boolean is(String status);
}
