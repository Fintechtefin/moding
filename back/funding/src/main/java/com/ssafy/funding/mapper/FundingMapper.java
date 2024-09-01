package com.ssafy.funding.mapper;

import com.ssafy.common.dto.response.FundingInfoResponse;
import com.ssafy.funding.domain.Funding;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface FundingMapper {
    @Mapping(source = "funding.time", target = "startTime")
    @Mapping(source = "count", target = "count")
    @Mapping(expression = "java(funding.getPoster())", target = "poster")
    @Mapping(expression = "java(funding.getAge())", target = "age")
    @Mapping(expression = "java(funding.getTitle())", target = "title")
    @Mapping(expression = "java(funding.getRunningTime())", target = "runningTime")
    @Mapping(expression = "java(funding.getName())", target = "name")
    @Mapping(expression = "java(funding.getNumber())", target = "number")
    FundingInfoResponse fundingToFundingInfoResponse(Funding funding, Integer count);
}
