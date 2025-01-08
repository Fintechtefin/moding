package com.ssafy.funding.mapper;

import com.ssafy.common.dto.response.FundingInfoResponse;
import com.ssafy.funding.domain.Funding;
import com.ssafy.funding.domain.Order;
import com.ssafy.funding.dto.response.JoinFundingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
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

    @Mapping(expression = "java(order.getMovieId())", target = "movieId")
    @Mapping(source = "order.uuid", target = "orderUuid")
    @Mapping(expression = "java(order.getMovieTitle())", target = "movieTitle")
    @Mapping(expression = "java(order.getMoviePoster())", target = "moviePoster")
    @Mapping(expression = "java(order.getFundingEndAt())", target = "endAt")
    @Mapping(expression = "java(order.getFundingPeopleCount())", target = "recruitedCount")
    @Mapping(source = "participantCount", target = "participantCount")
    JoinFundingResponse toJoinFundingResponse(Order order, Integer participantCount);
}
