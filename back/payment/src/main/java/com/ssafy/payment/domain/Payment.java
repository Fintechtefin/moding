package com.ssafy.payment.domain;

import com.ssafy.payment.dto.response.PaymentsResponse;
import java.time.ZonedDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @Column(nullable = false)
    private Long orderId; // 주문 PK

    @Column(nullable = false)
    private String paymentKey; // 결제 건에 대한 고유한 키값

    private ZonedDateTime approvedAt; // 결제 승인이 일어난 시간정보

    @Column(nullable = false)
    private ZonedDateTime requestedAt; // 결제 일어난 시간정보

    // @OneToOne private PaymentCancel paymentCancel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    // @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_status_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentStatus paymentStatus;

    public static Payment createPayment(
            Long orderId,
            PaymentsResponse paymentsResponse,
            PaymentMethod paymentMethod,
            PaymentStatus paymentStatus) {
        return Payment.builder()
                .paymentKey(paymentsResponse.getPaymentKey())
                .orderId(orderId)
                .requestedAt(paymentsResponse.getRequestedAt())
                .approvedAt(
                        paymentsResponse.getApprovedAt() == null
                                ? null
                                : paymentsResponse.getApprovedAt())
                .paymentMethod(paymentMethod)
                .paymentStatus(paymentStatus)
                .build();
    }

    public void changePaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
