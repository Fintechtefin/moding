package com.ssafy.payment.controller;

import com.ssafy.payment.config.PaymentsConfirmConfig;
import com.ssafy.payment.dto.request.ConfirmPaymentsRequest;
import com.ssafy.payment.dto.response.PaymentsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "PaymentsConfirmClient",
        url = "https://api.tosspayments.com",
        configuration = {PaymentsConfirmConfig.class})
public interface PaymentsConfirmClient {
    @PostMapping("/v1/payments/confirm")
    PaymentsResponse execute(@RequestBody ConfirmPaymentsRequest confirmPaymentsRequest);
}
