package com.ssafy.funding.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderMethod {
    APPROVAL("APPROVAL", "승인 방식"),
    PAYMENT("PAYMENT", "결제 방식");

    private String value;

    @JsonValue private String kr;

    public Boolean isPayment() {
        return this.equals(OrderMethod.PAYMENT);
    }
}
