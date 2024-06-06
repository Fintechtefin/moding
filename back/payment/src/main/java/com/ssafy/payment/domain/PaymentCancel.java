package com.ssafy.payment.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Builder
@AllArgsConstructor
public class PaymentCancel implements Serializable {
    @Id private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "payment_cancel_id")
    private Payment payment;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "requested_at")
    private LocalDateTime requestedAt;

    private Long amount;
}
