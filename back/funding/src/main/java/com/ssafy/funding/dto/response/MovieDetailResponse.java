package com.ssafy.funding.dto.response;

import com.ssafy.funding.domain.FundingStatus;
import com.ssafy.funding.domain.Movie;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetailResponse implements Serializable {

    @NotNull
    @Schema(description = "영화 아이디")
    private int movieId;

    @NotNull
    @Schema(description = "영화 제목")
    private String title;

    @NotNull
    @Schema(description = "영화 포스터")
    private String poster;

    @NotNull
    @Schema(description = "현재 상태")
    private FundingStatus status;

    @Schema(description = "좋아요 수")
    private long likeCnt;

    //    @Schema(description = "펀딩 개최 횟수")
    //    private int success;

    public static MovieDetailResponse of(Movie movie) {
        MovieDetailResponse movieDetailResponse = new MovieDetailResponse();
        movieDetailResponse.movieId = movie.getId();
        movieDetailResponse.title = movie.getTitle();
        movieDetailResponse.poster = movie.getPoster();
        //        detailMovie.status = FundingStatus.valueOf(movie.getStatus().getKr()).getKr(); //
        // 확인 필요

        return movieDetailResponse;
    }
}
