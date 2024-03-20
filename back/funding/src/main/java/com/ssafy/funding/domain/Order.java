package com.ssafy.funding.domain;

import com.ssafy.funding.domain.validator.OrderValidator;
import com.ssafy.funding.exception.NotPendingOrderException;
import com.ssafy.funding.exception.OrderFundingNotFountException;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    // 주문한 유저아이디
    @Column(nullable = false)
    private Long userId;

    @OneToOne(mappedBy = "order")
    private UserOrder userOrder;

    // 토스페이먼츠용 주문번호
    @Column(nullable = false)
    private String uuid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderMethod orderMethod;

    @OneToMany private List<OrderFunding> orderFunding;

    /** 선착순 방식의 결제입니다. */
    public void confirm(Long currentUserId, OrderValidator orderValidator) {
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
        return orderFunding.stream()
                .findFirst()
                .orElseThrow(() -> OrderFundingNotFountException.EXCEPTION);
    }

    /** 펀딩의 아이디를 가져옵니다. */
    public Integer getFundingId() {
        return getOrderFunding().get(0).getFunding().getId();
    }
}
