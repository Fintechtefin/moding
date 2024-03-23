package com.ssafy.payment.service;

import com.ssafy.payment.controller.PaymentsCancelClient;
import com.ssafy.payment.dto.request.CancelPaymentsRequest;
import com.ssafy.payment.dto.response.PaymentsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WithdrawPaymentService {
    private final PaymentsCancelClient paymentsCancelClient;

    public PaymentsResponse execute(String orderUuid, String paymentKey, String reason) {
        log.info("취소처리 " + orderUuid + " : " + paymentKey + reason);
        return paymentsCancelClient.execute(
                orderUuid,
                paymentKey,
                CancelPaymentsRequest.builder().cancelReason(reason).build());
    }
}
