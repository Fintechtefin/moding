package com.ssafy.funding.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Builder
@AllArgsConstructor
public class UserOrder {
    @Id private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_order_id", referencedColumnName = "id")
    private Order order;

    @Column(nullable = false)
    private Integer userId;
}
