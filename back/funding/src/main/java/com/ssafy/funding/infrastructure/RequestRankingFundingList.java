package com.ssafy.funding.infrastructure;

import com.ssafy.funding.repository.FundingRepository;

public class RequestRankingFundingList implements FundingList {
    private final String STATUS = "request";

    @Override
    public Object getFundingList(FundingRepository fundingRepository) {
        return fundingRepository.getRequestRanking();
    }

    @Override
    public boolean is(String status) {
        return STATUS.equals(status);
    }
}
