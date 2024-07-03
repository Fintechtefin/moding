package com.ssafy.payment.service;

import static com.ssafy.payment.exception.CustomExceptionStatus.NOT_FOUND_PAYMENT_METHOD;
import static com.ssafy.payment.exception.CustomExceptionStatus.NOT_FOUND_PAYMENT_STATUS;

import com.ssafy.payment.domain.Payment;
import com.ssafy.payment.domain.PaymentCancel;
import com.ssafy.payment.domain.PaymentMethod;
import com.ssafy.payment.domain.PaymentStatus;
import com.ssafy.payment.domain.repository.PaymentCancelRepository;
import com.ssafy.payment.domain.repository.PaymentMethodRepository;
import com.ssafy.payment.domain.repository.PaymentRepository;
import com.ssafy.payment.domain.repository.PaymentStatusRepository;
import com.ssafy.payment.dto.response.PaymentsResponse;
import com.ssafy.payment.exception.BadRequestException;
import com.ssafy.payment.exception.NotFoundPaymentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentFacadeRepository {
    private final PaymentCancelRepository paymentCancelRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentStatusRepository paymentStatusRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethod findPaymentMethod() {
        return paymentMethodRepository
                .findById(1)
                .orElseThrow(() -> new BadRequestException(NOT_FOUND_PAYMENT_METHOD));
    }

    public PaymentStatus findPaymentStatus() {
        return paymentStatusRepository
                .findById(4)
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

    public PaymentStatus findPaymentStatusById() {
        return paymentStatusRepository
                .findById(5)
                .orElseThrow(() -> new BadRequestException(NOT_FOUND_PAYMENT_STATUS));
    }
}
