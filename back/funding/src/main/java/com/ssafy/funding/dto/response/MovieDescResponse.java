package com.ssafy.funding.dto.response;

import com.ssafy.funding.domain.FundingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    //    @Schema(description = "지역별 설문 참여 수")
    //    private Map<String, Integer> areaCnt = new HashMap<>();
    //
    //    @Schema(description = "시간별 설문 참여 수")
    //    private Map<String, Integer> timeCnt = new HashMap<>();

    @Schema(description = "누적 요청 수")
    private int total;

    @Schema(description = "장르")
    private List<String> genre = new ArrayList<>();

    @Schema(description = "펀딩 성공 횟수")
    private int success;

    public static MovieDescResponse of(MovieSummaryResponse movieSummaryResponse) {
        return MovieDescResponse.builder()
                .movieId(movieSummaryResponse.getMovieId())
                .title(movieSummaryResponse.getTitle())
                .status(FundingStatus.valueOf(String.valueOf(movieSummaryResponse.getStatus())))
                .releaseAt(movieSummaryResponse.getReleaseAt())
                .runningTime(movieSummaryResponse.getRunningTime())
                .age(movieSummaryResponse.getAge())
                .actors(movieSummaryResponse.getActors())
                .plot(movieSummaryResponse.getPlot())
                .poster(movieSummaryResponse.getPoster())
                .hopeCnt(movieSummaryResponse.getHopeCnt())
                .build();
    }

    // 누적 요청 수 저장
    public static MovieDescResponse setTotal(MovieDescResponse movieDescResponse, int total) {
        movieDescResponse.total = total;
        return movieDescResponse;
    }

    // 장르 저장
    public static MovieDescResponse setGenre(
            MovieDescResponse movieDescResponse, List<String> genreList) {
        movieDescResponse.genre = genreList;
        return movieDescResponse;
    }

    public static MovieDescResponse setSuccess(MovieDescResponse movieDescResponse, int success) {
        movieDescResponse.success = success;
        return movieDescResponse;
    }
}
