package com.ssafy.funding.controller.feign;

import com.ssafy.funding.dto.request.ConfirmPaymentsRequest;
import com.ssafy.funding.dto.request.RefundOrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT")
public interface PaymentFeignClient {

    //    @PostMapping("/payments")
    //    void callCreatePayment(@RequestBody ConfirmPaymentsRequest confirmPaymentsRequest);

    @PostMapping("/payments/confirm")
    void callCreatePayment(@RequestBody ConfirmPaymentsRequest confirmPaymentsRequest);

    @PostMapping("/payments/refund")
    void callRefundPayment(@RequestBody RefundOrderRequest refundOrderRequest);
}
