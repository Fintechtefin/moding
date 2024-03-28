package com.ssafy.funding.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieFundingRequest {
    private int movieId;
    private String userId;
    private int time;
    private String location;
}
