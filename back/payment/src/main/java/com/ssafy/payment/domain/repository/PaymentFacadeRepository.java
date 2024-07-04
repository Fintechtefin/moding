package com.ssafy.payment.domain.repository;

import static com.ssafy.payment.exception.CustomExceptionStatus.NOT_FOUND_PAYMENT_METHOD;
import static com.ssafy.payment.exception.CustomExceptionStatus.NOT_FOUND_PAYMENT_STATUS;

import com.ssafy.payment.domain.Payment;
import com.ssafy.payment.domain.PaymentCancel;
import com.ssafy.payment.domain.PaymentMethod;
import com.ssafy.payment.domain.PaymentStatus;
import com.ssafy.payment.dto.response.PaymentsResponse;
import com.ssafy.payment.exception.BadRequestException;
import com.ssafy.payment.exception.NotFoundPaymentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentFacadeRepository {
    private static final int PAYMENT_METHOD_CARD = 1;
    private static final int PAYMENT_STATUS_DONE = 4;
    private static final int PAYMENT_STATUS_CANCELED = 5;

    private final PaymentCancelRepository paymentCancelRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentStatusRepository paymentStatusRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethod findPaymentMethodCard() {
        return paymentMethodRepository
                .findById(PAYMENT_METHOD_CARD)
                .orElseThrow(() -> new BadRequestException(NOT_FOUND_PAYMENT_METHOD));
    }

    public PaymentStatus findPaymentStatusDone() {
        return paymentStatusRepository
                .findById(PAYMENT_STATUS_DONE)
                .orElseThrow(() -> new BadRequestException(NOT_FOUND_PAYMENT_STATUS));
    }

    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    public Payment findPaymentByOrderId(final Long orderId) {
        return paymentRepository
                .findByOrderId(orderId)
                .orElseThrow(() -> NotFoundPaymentException.EXCEPTION);
    }

    public void savePaymentCancel(final Payment payment, final PaymentsResponse paymentsResponse) {
        paymentCancelRepository.save(
                PaymentCancel.builder()
                        .payment(payment)
                        .approvedAt(paymentsResponse.getApprovedAt().toLocalDateTime())
                        .requestedAt(paymentsResponse.getRequestedAt().toLocalDateTime())
                        .amount(paymentsResponse.getTotalAmount())
                        .build());
    }

    public PaymentStatus findPaymentStatusCanceled() {
        return paymentStatusRepository
                .findById(PAYMENT_STATUS_CANCELED)
                .orElseThrow(() -> new BadRequestException(NOT_FOUND_PAYMENT_STATUS));
    }
}
