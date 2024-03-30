package com.ssafy.funding.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RequestMovieListResponse {

    private final List<RequestMovieResponse> requestMovieResponseList;


    public static RequestMovieListResponse of(List<RequestMovieResponse> requestMovieResponse) {
        return RequestMovieListResponse.builder()
                .requestMovieResponseList(requestMovieResponse)
                .build();
    }
}