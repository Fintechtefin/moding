package com.ssafy.funding.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.funding.domain.Order;
import com.ssafy.funding.dto.request.CreatePaymentsRequest;
import com.ssafy.funding.dto.request.RefundOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendCreateOrderRequest(String topic, Order order) {
        kafkaTemplate.send(topic, convertToJson(convertTo(order)));
    }

    public void sendRefundOrderRequest(String topic, RefundOrderRequest refundOrderRequest) {
        kafkaTemplate.send(topic, convertRefundOrderRequestToJson(refundOrderRequest));
    }

    private String convertToJson(CreatePaymentsRequest createPaymentsRequest) {
        try {
            return new ObjectMapper().writeValueAsString(createPaymentsRequest);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private String convertRefundOrderRequestToJson(RefundOrderRequest refundOrderRequest) {
        try {
            return new ObjectMapper().writeValueAsString(refundOrderRequest);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private CreatePaymentsRequest convertTo(Order order) {
        return CreatePaymentsRequest.builder()
                .orderId(order.getUuid()) // 주문 번호
                .method(order.getOrderMethod().getValue())
                .amount(Long.valueOf(order.getCount() * order.getPrice()))
                .orderName("주문")
                .successUrl("http://localhost:8082")
                .failUrl("http://localhost:8082")
                .build();
    }
}
