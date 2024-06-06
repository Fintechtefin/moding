package com.ssafy.payment.messaging;

import com.ssafy.payment.controller.PaymentsCancelClient;
import com.ssafy.payment.service.WithdrawPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {
    private final WithdrawPaymentService withdrawPaymentService;
    private final PaymentsCancelClient paymentsCancelClient;

    @Value("${kafka.topics.payment}")
    private String topic;

    @KafkaListener(
            topics = "${kafka.topics.payment}",
            groupId = "${spring.kafka.consumer.group-id}")
    void listen(String refundOrderRequest) {
        log.info("success to recieve");
        // productService.orderProdcut(message);
        try {
            withdrawPaymentService.execute(refundOrderRequest);
            // testService.callPaymentCreateClient(createPaymentsRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
