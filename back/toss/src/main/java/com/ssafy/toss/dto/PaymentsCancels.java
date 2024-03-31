package com.ssafy.toss.dto;

import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentsCancels {
    private Long cancelAmount;
    private String cancelReason;
    private Long taxFreeAmount;
    private Long taxExceptionAmount;
    private Long refundableAmount;

    private Long easyPayDiscountAmount;

    private ZonedDateTime canceledAt;

    private String transactionKey;
}
