package com.ssafy.funding.dto.response;

import com.ssafy.funding.domain.Order;
import com.ssafy.funding.domain.OrderMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderResponse {

    // 총 결제 정보
    // @Schema(description = "결제 정보")
    // private final OrderPaymentResponse paymentInfo;

    @Schema(description = "주문 고유 uuid")
    private final String orderUuid;

    @Schema(description = "주문 방식 ( 결제 방식 , 승인 방식 )")
    private final OrderMethod orderMethod;

    public static OrderResponse of(Order order) {
        return OrderResponse.builder()
                .orderMethod(order.getOrderMethod())
                // .paymentInfo(OrderPaymentResponse.from(order))
                .orderUuid(order.getUuid())
                .build();
    }
}
