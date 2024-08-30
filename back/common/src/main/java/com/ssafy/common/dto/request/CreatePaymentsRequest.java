package com.ssafy.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
