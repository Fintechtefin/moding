package com.ssafy.funding.dto.response;

import com.ssafy.funding.domain.FundingStatus;
import com.ssafy.funding.domain.Movie;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class MovieRankingResponse implements Serializable {

    @NotNull
    @Schema(description = "영화 아이디")
    private int movieId;

    @NotNull
    @Schema(description = "영화 포스터")
    private String poster;

    @NotNull
    @Schema(description = "현재 상태")
    private FundingStatus status;

    @Schema(description = "검색량")
    private long searchCnt;

    @Schema(description = "몇시 기준 랭킹")
    private int refreshTime;

    public static MovieRankingResponse of(Movie movie) {
        MovieRankingResponse movieRankingResponse =
                MovieRankingResponse.builder()
                        .movieId(movie.getId())
                        .poster(movie.getPoster())
                        .status(movie.getStatus())
                        .build();

        return movieRankingResponse;
    }
}
