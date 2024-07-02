package com.ssafy.payment.service;

import static com.ssafy.payment.exception.CustomExceptionStatus.*;

import com.ssafy.payment.controller.PaymentsCancelClient;
import com.ssafy.payment.controller.PaymentsConfirmClient;
import com.ssafy.payment.domain.Payment;
import com.ssafy.payment.domain.PaymentCancel;
import com.ssafy.payment.domain.PaymentMethod;
import com.ssafy.payment.domain.PaymentStatus;
import com.ssafy.payment.domain.repository.PaymentCancelRepository;
import com.ssafy.payment.domain.repository.PaymentMethodRepository;
import com.ssafy.payment.domain.repository.PaymentRepository;
import com.ssafy.payment.domain.repository.PaymentStatusRepository;
import com.ssafy.payment.dto.request.CancelPaymentsRequest;
import com.ssafy.payment.dto.request.ConfirmPaymentsRequest;
import com.ssafy.payment.dto.request.RefundOrderRequest;
import com.ssafy.payment.dto.response.PaymentsResponse;
import com.ssafy.payment.exception.BadRequestException;
import com.ssafy.payment.exception.NotFoundPaymentException;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentsConfirmClient paymentsConfirmClient;
    private final PaymentsCancelClient paymentsCancelClient;
    private final PaymentCancelRepository paymentCancelRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentStatusRepository paymentStatusRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    @Transactional
    public void callTossPayConfirm(ConfirmPaymentsRequest confirmPaymentsRequest) {
        PaymentsResponse paymentsResponse =
                paymentsConfirmClient.execute(
                        ConfirmPaymentsRequest.builder()
                                .paymentKey(confirmPaymentsRequest.getPaymentKey())
                                .orderId(confirmPaymentsRequest.getOrderId())
                                .amount(confirmPaymentsRequest.getAmount())
                                .build());

        //        PaymentsResponse paymentsResponse = PaymentsResponse.builder()
        //                .paymentKey(confirmPaymentsRequest.getPaymentKey())
        //                .requestedAt(ZonedDateTime.now())
        //                .approvedAt(ZonedDateTime.now())
        //                .build();

        PaymentMethod paymentMethod =
                paymentMethodRepository
                        .findById(1)
                        .orElseThrow(() -> new BadRequestException(NOT_FOUND_PAYMENT_METHOD));
        PaymentStatus paymentStatus =
                paymentStatusRepository
                        .findById(4)
                        .orElseThrow(() -> new BadRequestException(NOT_FOUND_PAYMENT_STATUS));

        paymentRepository.save(
                Payment.createPayment(
                        confirmPaymentsRequest.getId(),
                        paymentsResponse,
                        paymentMethod,
                        paymentStatus));
    }

    @Transactional
    public void callTossPayRefund(RefundOrderRequest refundOrderRequest) {
        log.info(
                "취소처리 "
                        + refundOrderRequest.getOrderId()
                        + " : "
                        + refundOrderRequest.getCancelReason());

        Payment payment =
                paymentRepository
                        .findByOrderId(refundOrderRequest.getOrderId())
                        .orElseThrow(() -> NotFoundPaymentException.EXCEPTION);

        PaymentsResponse paymentsResponse =
                paymentsCancelClient.execute(
                        UUID.randomUUID().toString(), // Redis에 저장하는 로직으로 바꾸기
                        payment.getPaymentKey(),
                        CancelPaymentsRequest.builder()
                                .cancelReason(refundOrderRequest.getCancelReason())
                                .build());

        paymentCancelRepository.save(
                PaymentCancel.builder()
                        .payment(payment)
                        .approvedAt(paymentsResponse.getApprovedAt().toLocalDateTime())
                        .requestedAt(paymentsResponse.getRequestedAt().toLocalDateTime())
                        .amount(paymentsResponse.getTotalAmount())
                        .build());

        payment.changePaymentStatus(
                paymentStatusRepository
                        .findById(5)
                        .orElseThrow(() -> new BadRequestException(NOT_FOUND_PAYMENT_STATUS)));
    }
}
