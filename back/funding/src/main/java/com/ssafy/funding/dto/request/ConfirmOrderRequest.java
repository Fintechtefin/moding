package com.ssafy.funding.dto.request;

import lombok.Getter;

@Getter
public class ConfirmOrderRequest {
    private String paymentKey;
    private Long amount;
}
