package com.ssafy.funding.domain;

import com.ssafy.funding.exception.NotSupportedPaymentMethodException;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethod {
    CARD("카드"),
    VIRTUAL_ACCOUNT("가상계좌"),
    SIMPLE_PAYMENT("간편계좌"),
    MOBILE_PHONE("휴대폰"),
    ACCOUNT_TRANSFER("계좌이체"),
    CULTURAL_GIFT_CERTIFICATE("문화상품권"),
    BOOK_CULTURE_GIFT_CERTIFICATE("도서문화상품권"),
    GAME_CULTURE_GIFT_CERTIFICATE("게임문화상품권");

    private String method;

    public static PaymentMethod of(String requestMethod) {
        return Arrays.stream(PaymentMethod.values())
                .filter(value -> value.method.equals(requestMethod))
                .findFirst()
                .orElseThrow(() -> NotSupportedPaymentMethodException.EXCEPTION);
    }
}
