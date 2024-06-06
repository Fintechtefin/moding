package com.ssafy.funding.dto.response;

import com.ssafy.funding.domain.FundingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieSummaryResponse implements Serializable {

    @NotNull
    @Schema(description = "영화 아이디")
    private int movieId;

    @NotNull
    @Schema(description = "영화 제목")
    private String title;

    @Schema(description = "현재 상태")
    private FundingStatus status;

    @Schema(description = "개봉 날짜")
    private String releaseAt;

    @NotNull
    @Schema(description = "러닝타임")
    private int runningTime;

    @NotNull
    @Schema(description = "관람 등급")
    private String age;

    @NotNull
    @Schema(description = "출연배우")
    private String actors;

    @NotNull
    @Schema(description = "줄거리")
    private String plot;

    @NotNull
    @Schema(description = "영화 포스터")
    private String poster;

    @Schema(description = "현재 펀딩 요청 수")
    private long hopeCnt;
}
