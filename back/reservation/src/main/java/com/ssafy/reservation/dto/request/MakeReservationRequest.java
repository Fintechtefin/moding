package com.ssafy.reservation.dto.request;

import com.ssafy.reservation.dto.ListSeat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MakeReservationRequest {

    @Schema(description = "회원 번호")
    private Integer userId;

    @Schema(description = "좌석 위치")
    private ListSeat seats;

    @Schema(description = "예매할 펀딩 번호")
    private Integer fundingId;
}
