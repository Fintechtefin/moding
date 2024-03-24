package com.ssafy.payment.payload;

import com.ssafy.payment.messaging.OrderEventType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentEventPayload {
    private Long id;
    private String orderId;
    private String method;
    private Integer amount;
    private String orderName;
    private final String successUrl;
    private final String failUrl;
    private OrderEventType eventType;
}
