package com.ssafy.payment.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentEasyPay {
    private EasyPayCode provider;
    private Long amount;
    private Long discountAmount;
}
