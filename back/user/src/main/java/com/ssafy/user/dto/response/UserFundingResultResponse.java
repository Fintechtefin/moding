package com.ssafy.user.dto.response;

import static lombok.AccessLevel.PRIVATE;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class UserFundingResultResponse {

    @Schema(description = "영화 pk")
    private int movieId;

    @Schema(description = "영화 제목")
    private String title;

    @Schema(description = "포스터")
    private String poster;

    @Schema(description = "상영일")
    private LocalDate date;

    @Schema(description = "펀딩 참여 인원")
    private int attendCnt;

    @Schema(description = "펀딩 목표 인원")
    private int goalCnt;

    @Schema(description = "예매 번호")
    private int reservationId;

    @Schema(description = "펀딩 결과")
    private boolean fundingFinalResult;
}
