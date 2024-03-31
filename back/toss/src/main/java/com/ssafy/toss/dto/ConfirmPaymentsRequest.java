package com.ssafy.toss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmPaymentsRequest {
    private String paymentKey;
    private String orderId;
    private Long amount;
    private Long id; // 주문 PK
}
