package com.ssafy.payment.service;

import static com.ssafy.payment.exception.CustomExceptionStatus.*;

import com.ssafy.common.dto.request.CancelPaymentsRequest;
import com.ssafy.common.dto.request.ConfirmPaymentsRequest;
import com.ssafy.payment.controller.PaymentsCancelClient;
import com.ssafy.payment.controller.PaymentsConfirmClient;
import com.ssafy.payment.domain.Payment;
import com.ssafy.payment.domain.PaymentMethod;
import com.ssafy.payment.domain.PaymentStatus;
import com.ssafy.payment.domain.repository.PaymentFacadeRepository;
import com.ssafy.payment.dto.request.RefundOrderRequest;
import com.ssafy.payment.dto.response.PaymentsResponse;
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
    private final PaymentFacadeRepository paymentFacadeRepository;

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

        PaymentMethod paymentMethod = paymentFacadeRepository.findPaymentMethodCard();
        PaymentStatus paymentStatus = paymentFacadeRepository.findPaymentStatusDone();

        paymentFacadeRepository.savePayment(
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
                paymentFacadeRepository.findPaymentByOrderId(refundOrderRequest.getOrderId());

        PaymentsResponse paymentsResponse =
                paymentsCancelClient.execute(
                        UUID.randomUUID().toString(), // Redis에 저장하는 로직으로 바꾸기
                        payment.getPaymentKey(),
                        new CancelPaymentsRequest(refundOrderRequest.getCancelReason()));

        paymentFacadeRepository.savePaymentCancel(payment, paymentsResponse);

        payment.changePaymentStatus(paymentFacadeRepository.findPaymentStatusCanceled());
    }
}
