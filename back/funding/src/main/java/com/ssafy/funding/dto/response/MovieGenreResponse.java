package com.ssafy.funding.dto.response;

import com.ssafy.funding.domain.FundingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MovieGenreResponse {

    private int movieId;
    private String poster;
    private FundingStatus status;
}
