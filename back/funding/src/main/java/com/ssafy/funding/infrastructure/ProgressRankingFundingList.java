package com.ssafy.funding.infrastructure;

import com.ssafy.funding.repository.FundingRepository;

public class ProgressRankingFundingList implements FundingList {
    private static final String STATUS = "progress";

    @Override
    public Object getFundingList(FundingRepository fundingRepository) {
        return fundingRepository.getProgressRanking();
    }

    @Override
    public boolean is(String status) {
        return STATUS.equals(status);
    }
}
