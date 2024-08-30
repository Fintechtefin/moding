package com.ssafy.payment.controller;

import com.ssafy.common.dto.request.ConfirmPaymentsRequest;
import com.ssafy.payment.dto.response.PaymentsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "TOSS")
public interface MockServerFeignClient {
    @PostMapping("/toss/v1/payments/confirm")
    PaymentsResponse callCreatePaymentFromMockServer(
            @RequestBody ConfirmPaymentsRequest confirmPaymentsRequest);
}
