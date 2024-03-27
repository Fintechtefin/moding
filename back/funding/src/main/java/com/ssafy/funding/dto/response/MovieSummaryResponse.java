package com.ssafy.funding.dto.response;

import com.ssafy.funding.domain.FundingStatus;
import com.ssafy.funding.domain.Movie;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieSummaryResponse implements Serializable {

    @NotNull
    @Schema(description = "영화 아이디")
    private int movieId;

    @NotNull
    @Schema(description = "영화 제목")
    private String title;

    @NotNull
    @Schema(description = "영화 관람 등급")
    private int age;

    @NotNull
    @Schema(description = "영화 포스터")
    private String poster;

//    @Schema(description = "개봉날짜")
//
//
//    @NotNull
//    @Schema(description = "현재 상태")
//    private FundingStatus status;

    @Schema(description = "좋아요 수")
    private long likeCnt;



}