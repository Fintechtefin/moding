package com.ssafy.funding.domain;

import com.ssafy.funding.domain.validator.OrderValidator;
import com.ssafy.funding.dto.Money;
import com.ssafy.funding.exception.NotPendingOrderException;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    // 주문한 유저아이디
    @Column(nullable = false)
    private Integer userId;

    // 토스페이먼츠용 주문번호
    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer count;

    // 주문 상태
    private boolean status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderMethod orderMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id")
    private Funding funding;

    /** 선착순 방식의 결제입니다. */
    public void confirm(Integer currentUserId, OrderValidator orderValidator) {
        orderValidator.validOwner(this, currentUserId);
    }

    /** 주문 상태가 결제방식의 승인 가능한 상태인지 검증합니다. */
    public void validStatusCanPaymentConfirm(OrderStatus orderStatus) {
        if (!Objects.equals(orderStatus, OrderStatus.PENDING_PAYMENT)) {
            throw NotPendingOrderException.EXCEPTION;
        }
    }

    @PrePersist
    public void addUUID() {
        this.uuid = UUID.randomUUID().toString();
    }

    public static Order createPaymentOrder(
            Integer userId,
            Funding funding,
            Integer fundingCount,
            Integer fundingPrice,
            Long amount,
            OrderValidator orderValidator) {
        // Integer supplyAmount = fundingCount * fundingPrice;
        Order order =
                Order.builder()
                        .userId(userId)
                        .price(fundingPrice)
                        .count(fundingCount)
                        .status(true)
                        .orderMethod(OrderMethod.PAYMENT)
                        .funding(funding)
                        .build();

        orderValidator.validFundingIsOpen(funding);
        orderValidator.validFundingStockEnough(order, funding);
        /*orderValidator.validAmountIsSameAsRequest(
                        order, Money.wons(amount)); // 구매수량 * 펀딩 참여 금액이 요청 받은 총 금액과 일치?
        */
        return order;
    }

    /*
    총 결제 금액(펀딩의 가격 * 구매 갯수)을 가져옵니다.
     */
    public Money getTotalPaymentPrice() {
        Money money = Money.ZERO;
        for (int i = 0; i < this.getCount(); i++) {
            money = money.plus(Money.wons(this.price));
        }
        return money;
    }

    /** 사용자가 주문을 환불 시킵니다. */
    public void refund(Integer currentUserId, OrderValidator orderValidator) {
        orderValidator.validOwner(this, currentUserId);
        // orderValidator.validCanRefund(this);
        this.status = false;
    }
}
