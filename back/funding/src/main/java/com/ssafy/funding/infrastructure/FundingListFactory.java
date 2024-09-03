package com.ssafy.funding.infrastructure;

import com.ssafy.funding.exception.NotFoundFundingListTypeException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class FundingListFactory extends Factory {
    private final List<FundingList> fundingLists;

    public FundingListFactory(final List<FundingList> fundingLists) {
        this.fundingLists = fundingLists;
    }

    @Override
    public FundingList create(final String type) {
        return fundingLists.stream()
                .filter(status -> status.is(type))
                .findFirst()
                .orElseThrow(() -> NotFoundFundingListTypeException.EXCEPTION);
    }
}
