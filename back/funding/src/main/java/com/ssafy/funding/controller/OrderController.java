package com.ssafy.funding.controller;

import com.ssafy.funding.dto.request.CreateOrderRequest;
import com.ssafy.funding.messaging.Producer;
import com.ssafy.funding.service.OrderService;
import com.ssafy.funding.service.client.UserClient;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserClient userClient;
    private final Producer producer;

    @Operation(summary = "주문(펀딩)을 결제합니다.")
    @PostMapping
    public ResponseEntity<?> orderFunding(@RequestBody CreateOrderRequest createOrderRequest) {
        return ResponseEntity.ok(
                orderService.orderFunding(createOrderRequest) /*orderService.orderFunding(
                createOrderRequest, Integer.parseInt(userClient.findUserInfo().getUsername()))*/);
    }
}
