package com.ssafy.reservation.dto.response;

import com.ssafy.reservation.dto.ListSeat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketResponse {

    @Schema(description = "좌석 정보")
    private ListSeat seats;

    @Schema(description = "영화 포스터")
    private String poster;

    @Schema(description = "영화 관람 등급")
    private String age;

    @Schema(description = "재상영 날짜")
    private LocalDate date;

    @Schema(description = "시작 시간")
    private String startTime;

    @Schema(description = "종료 시간")
    private String endTime;

    @Schema(description = "수량")
    private Integer count;

    @Schema(description = "영화관 지점명")
    private String name;

    @Schema(description = "상영관")
    private Integer number;
}
