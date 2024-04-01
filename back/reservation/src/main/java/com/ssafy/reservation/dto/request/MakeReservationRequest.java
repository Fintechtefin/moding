package com.ssafy.reservation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MakeReservationRequest {

    @Schema(description = "좌석 위치")
    private List<String> position;

    @Schema(description = "예매할 펀딩 번호")
    private Integer fundingId;
}
