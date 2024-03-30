package com.ssafy.funding.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestMovieResponse {

    @Schema(description = "영화 번호")
    private final Integer movieId;

    @Schema(description = "영화 제목")
    private final String title;

    @Schema(description = "영화 포스터")
    private final String poster;

    @Schema(description = "펀딩 모집 인원")
    private final Integer requestCnt;
}
