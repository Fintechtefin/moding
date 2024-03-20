package com.ssafy.funding.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Builder
@Getter
@AllArgsConstructor
@Table(name = "orders_funding")
public class OrderFunding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_funding_id")
    private Integer id;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer count;

    // 주문 상태
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id")
    private Funding funding;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public static OrderFunding createOrderFunding(
            Integer price, Integer count, Funding funding, Order order) {
        return OrderFunding.builder()
                .price(price)
                .count(count)
                .status(true)
                .funding(funding)
                .order(order)
                .build();
    }

    /** 펀딩 아이디를 조회합니다. */
    public Integer getFundingId() {
        return funding.getId();
    }
}
