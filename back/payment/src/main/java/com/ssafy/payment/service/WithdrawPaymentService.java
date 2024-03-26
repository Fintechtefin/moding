package com.ssafy.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.payment.controller.PaymentsCancelClient;
import com.ssafy.payment.domain.Payment;
import com.ssafy.payment.domain.PaymentCancel;
import com.ssafy.payment.dto.request.CancelPaymentsRequest;
import com.ssafy.payment.dto.request.RefundOrderRequest;
import com.ssafy.payment.dto.response.PaymentsResponse;
import com.ssafy.payment.exception.NotFoundPaymentException;
import com.ssafy.payment.repository.PaymentCancelRepository;
import com.ssafy.payment.repository.PaymentRepository;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WithdrawPaymentService {
    private final PaymentsCancelClient paymentsCancelClient;
    private final PaymentRepository paymentRepository;
    private final PaymentCancelRepository paymentCancelRepository;

    @Transactional
    public void execute(String refundOrderRequest) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        RefundOrderRequest refundRequest =
                objectMapper.readValue(
                        refundOrderRequest, new TypeReference<RefundOrderRequest>() {});

        log.info("취소처리 " + refundRequest.getOrderId() + " : " + refundRequest.getCancelReason());

        Payment payment =
                paymentRepository
                        .findByOrderId(refundRequest.getOrderId())
                        .orElseThrow(() -> NotFoundPaymentException.EXCEPTION);

        PaymentsResponse paymentsResponse =
                paymentsCancelClient.execute(
                        UUID.randomUUID().toString(), // Redis에 저장하는 로직으로 바꾸기
                        payment.getPaymentKey(),
                        CancelPaymentsRequest.builder()
                                .cancelReason(refundRequest.getCancelReason())
                                .build());

        paymentCancelRepository.save(
                PaymentCancel.builder()
                        .payment(payment)
                        .approvedAt(paymentsResponse.getApprovedAt().toLocalDateTime())
                        .requestedAt(paymentsResponse.getRequestedAt().toLocalDateTime())
                        .amount(paymentsResponse.getTotalAmount())
                        .build());
    }
}
