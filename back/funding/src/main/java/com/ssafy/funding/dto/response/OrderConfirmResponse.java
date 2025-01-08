package com.ssafy.funding.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderConfirmResponse {

    @Schema(description = "펀딩내역")
    private final String fundingTitle;

    @Schema(description = "결제일자")
    private final LocalDateTime approvedAt;

    @Schema(description = "결제금액")
    private final Integer amount;
}
