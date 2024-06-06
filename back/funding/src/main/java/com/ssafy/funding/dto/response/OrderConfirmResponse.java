package com.ssafy.funding.dto.response;

import com.ssafy.funding.domain.Order;
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

    public static OrderConfirmResponse of(Order order) {
        return OrderConfirmResponse.builder()
                .fundingTitle(order.getFunding().getMovie().getTitle())
                .approvedAt(order.getCreatedAt())
                .amount(order.getPrice())
                .build();
    }
}
