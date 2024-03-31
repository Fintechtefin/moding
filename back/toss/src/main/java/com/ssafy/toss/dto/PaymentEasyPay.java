package com.ssafy.toss.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentEasyPay {
    private EasyPayCode provider;
    private Long amount;
    private Long discountAmount;
}
