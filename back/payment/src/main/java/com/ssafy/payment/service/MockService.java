package com.ssafy.payment.service;

import static com.ssafy.payment.exception.CustomExceptionStatus.NOT_FOUND_PAYMENT_METHOD;
import static com.ssafy.payment.exception.CustomExceptionStatus.NOT_FOUND_PAYMENT_STATUS;

import com.ssafy.payment.controller.MockServerFeignClient;
import com.ssafy.payment.domain.Payment;
import com.ssafy.payment.domain.PaymentMethod;
import com.ssafy.payment.domain.PaymentStatus;
import com.ssafy.payment.domain.repository.PaymentMethodRepository;
import com.ssafy.payment.domain.repository.PaymentRepository;
import com.ssafy.payment.domain.repository.PaymentStatusRepository;
import com.ssafy.payment.dto.request.ConfirmPaymentsRequest;
import com.ssafy.payment.dto.response.PaymentsResponse;
import com.ssafy.payment.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MockService {
    private final MockServerFeignClient mockServerFeignClient;
    private final PaymentRepository paymentRepository;
    private final PaymentStatusRepository paymentStatusRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public void confirmPayment(ConfirmPaymentsRequest confirmPaymentsRequest) {

        PaymentsResponse paymentsResponse =
                mockServerFeignClient.callCreatePaymentFromMockServer(
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
}
