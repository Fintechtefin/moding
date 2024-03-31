package com.ssafy.toss.service;

import com.ssafy.toss.dto.ConfirmPaymentsRequest;
import com.ssafy.toss.dto.PaymentsResponse;
import java.time.ZonedDateTime;
import org.springframework.stereotype.Service;

@Service
public class TossPaymentService {

    public PaymentsResponse confirmPayment(ConfirmPaymentsRequest confirmPaymentsRequest) {
        return PaymentsResponse.builder()
                .paymentKey(confirmPaymentsRequest.getPaymentKey())
                .requestedAt(ZonedDateTime.now())
                .approvedAt(ZonedDateTime.now())
                .build();
    }
}
