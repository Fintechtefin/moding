package com.ssafy.funding.controller;

import com.ssafy.funding.dto.request.CreateOrderRequest;
import com.ssafy.funding.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "주문(펀딩)을 결제합니다.")
    @PostMapping
    public ResponseEntity<?> orderFunding(@RequestBody CreateOrderRequest createOrderRequest) {
        return ResponseEntity.ok(orderService.orderFunding(createOrderRequest));
    }
}
