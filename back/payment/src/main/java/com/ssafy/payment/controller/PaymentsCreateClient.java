package com.ssafy.payment.controller;

import com.ssafy.payment.config.PaymentsCreateConfig;
import com.ssafy.payment.dto.request.CreatePaymentsRequest;
import com.ssafy.payment.dto.response.PaymentsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "PaymentsCreateClient",
        url = "https://api.tosspayments.com",
        configuration = {PaymentsCreateConfig.class})
public interface PaymentsCreateClient {
    @PostMapping(path = "/v1/payments", consumes = MediaType.APPLICATION_JSON_VALUE)
    PaymentsResponse execute(@RequestBody CreatePaymentsRequest createPaymentsRequest);
}
