package com.ssafy.funding.mapper;

import com.ssafy.funding.domain.MovieFunding;
import com.ssafy.funding.dto.response.RequestMovieResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieFundingMapper {
    @Mapping(expression = "java(movieFunding.getMovieTitle())", target = "title")
    @Mapping(expression = "java(movieFunding.getIdOfMovie())", target = "movieId")
    @Mapping(expression = "java(movieFunding.getMoviePoster())", target = "poster")
    @Mapping(source = "requestCnt", target = "requestCnt")
    RequestMovieResponse toRequestMovieResponse(MovieFunding movieFunding, Integer requestCnt);
}
