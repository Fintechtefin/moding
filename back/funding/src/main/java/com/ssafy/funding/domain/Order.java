package com.ssafy.funding.domain;

import com.ssafy.funding.domain.validator.OrderValidator;
import com.ssafy.funding.exception.NotPendingOrderException;
import com.ssafy.funding.exception.OrderFundingNotFountException;
import java.util.ArrayList;
import java.util.List;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderMethod orderMethod;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<OrderFunding> orderFundings;

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

    /** 오더펀딩목록의 한 요소를 가져옵니다. */
    private OrderFunding getOrderFundingLine() {
        return orderFundings.stream()
                .findFirst()
                .orElseThrow(() -> OrderFundingNotFountException.EXCEPTION);
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
            OrderValidator orderValidator) {
        Integer supplyAmount = fundingCount * fundingPrice;
        Order order = Order.builder().userId(userId).orderMethod(OrderMethod.PAYMENT).build();
        return order;
    }

    public void addOrderFundings(
            List<OrderFunding> orderFundings, Funding funding, int fundingCount) {
        this.addAll(this, orderFundings, funding, fundingCount);
    }

    public void addAll(
            Order order, List<OrderFunding> orderFundings, Funding funding, int fundingCount) {
        this.orderFundings = new ArrayList<OrderFunding>();
        orderFundings.stream()
                .map(
                        orderFunding ->
                                OrderFunding.createOrderFunding(
                                        funding.getPrice(), fundingCount, funding, this))
                .forEach(this.orderFundings::add);
    }
}
