package com.ssafy.funding.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JoinFundingResponse {

    @Schema(description = "영화 번호")
    private final Integer movieId;

    @Schema(description = "주문 번호")
    private final Long id;

    @Schema(description = "랜덤으로 생성된 주문 번호")
    private final String orderUuid;

    @Schema(description = "영화 제목")
    private final String movieTitle;

    @Schema(description = "영화 포스터")
    private final String moviePoster;

    @Schema(description = "펀딩 종료 기간")
    private final LocalDateTime endAt;

    @Schema(description = "펀딩 참여 인원")
    private final Integer participantCount;

    @Schema(description = "펀딩 모집 인원")
    private final Integer recruitedCount;
}
