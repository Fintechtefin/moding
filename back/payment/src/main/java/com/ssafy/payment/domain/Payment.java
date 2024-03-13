package com.ssafy.payment.domain;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    private Long orderId;

    private LocalDateTime approvedAt;

    private LocalDateTime requestedAt;

    // @OneToOne private PaymentCancel paymentCancel;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    // @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_status_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentStatus paymentStatus;
}
