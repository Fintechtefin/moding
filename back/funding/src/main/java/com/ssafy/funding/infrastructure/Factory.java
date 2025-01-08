package com.ssafy.funding.infrastructure;

public abstract class Factory {
    public abstract FundingList create(final String status);
}
