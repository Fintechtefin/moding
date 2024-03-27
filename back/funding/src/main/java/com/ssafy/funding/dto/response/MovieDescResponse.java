package com.ssafy.funding.dto.response;

import com.ssafy.funding.domain.FundingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDescResponse implements Serializable {

    @NotNull
    @Schema(description = "영화 아이디")
    private int movieId;

    @NotNull
    @Schema(description = "영화 제목")
    private String title;

    @NotNull
    @Schema(description = "현재 상태")
    private FundingStatus status;

    @NotNull
    @Schema(description = "개봉 날짜")
    private String releaseAt;

    @NotNull
    @Schema(description = "러닝타임")
    private int runningTime;

    @NotNull
    @Schema(description = "관람연령")
    private String age;

    @NotNull
    @Schema(description = "영화 포스터")
    private String actors;

    @NotNull
    @Schema(description = "줄거리")
    private String plot;

    @NotNull
    @Schema(description = "포스터")
    private String poster;

    @Schema(description = "좋아요 수")
    private long likeCnt;

    @Schema(description = "현재 펀딩 요청 수")
    private long hopeCnt;

    @Schema(description = "지역별 설문 참여 수")
    private Map<String, Integer> areaCnt = new HashMap<>();

    @Schema(description = "시간별 설문 참여 수")
    private Map<String, Integer> timeCnt = new HashMap<>();

    @Schema(description = "누적 요청 수")
    private int total;

    @Schema(description = "펀딩 성공 횟수")
    private int success;
}
