package com.ssafy.payment.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {
    private final TestService testService;

    @Value("${kafka.topics.test}")
    private String topic;

    @KafkaListener(topics = "${kafka.topics.test}", groupId = "${spring.kafka.consumer.group-id}")
    void listen(String createPaymentsRequest) {
        log.info("success to recieve");
        // productService.orderProdcut(message);
        try {
            testService.callPaymentCreateClient(createPaymentsRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
