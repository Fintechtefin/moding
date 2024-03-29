package com.ssafy.funding.dto.response;

import static lombok.AccessLevel.PRIVATE;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class MovieLikeResponse {

    private final Boolean status;

    public static MovieLikeResponse of(Boolean status) {
        return new MovieLikeResponse(status);
    }
}
