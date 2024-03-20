package com.ssafy.funding.domain;

import com.ssafy.funding.dto.Money;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentInfo {

    // 총 결제금액
    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "payment_amount"))
    private Money paymentAmount;

    // 공급가액

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "supply_amount"))
    private Money supplyAmount;

    @Builder
    public PaymentInfo(Money paymentAmount, Money supplyAmount) {
        this.paymentAmount = paymentAmount;
        this.supplyAmount = supplyAmount;
    }
}
