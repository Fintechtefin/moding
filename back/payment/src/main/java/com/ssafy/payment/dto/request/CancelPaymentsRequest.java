package com.ssafy.payment.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CancelPaymentsRequest {
    private String cancelReason;
}
