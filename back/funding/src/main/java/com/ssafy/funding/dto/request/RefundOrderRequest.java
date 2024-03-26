package com.ssafy.funding.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RefundOrderRequest {
    private String cancelReason;
    private Long orderId; // 주문 PK
}
