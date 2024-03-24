package com.ssafy.payment.dto.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentsRequest {
    private Long id;

    private String method;

    private Long amount;

    private String orderId;

    private String orderName;

    private String successUrl;

    private String failUrl;
}
