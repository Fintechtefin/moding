package com.ssafy.funding.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConfirmPaymentsRequest {
    private final String paymentKey;
    private final String orderId;
    private final Long amount;
    private final Long id; // 주문 PK
}
