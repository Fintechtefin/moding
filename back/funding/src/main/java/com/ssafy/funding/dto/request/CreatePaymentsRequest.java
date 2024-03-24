package com.ssafy.funding.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentsRequest {
    private Long id; // order PK
    private String method;
    private Long amount;
    private String orderId;
    private String orderName;
    private String successUrl;
    private String failUrl;
}
