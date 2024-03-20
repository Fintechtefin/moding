package com.ssafy.funding.controller;

import com.ssafy.funding.dto.response.OrderResponse;
import com.ssafy.funding.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "주문(펀딩)을 결제합니다.")
    @PostMapping("/{order_uuid}")
    public OrderResponse orderFunding(@PathVariable("order_uuid") String orderUuid) {
        return orderService.orderFunding(orderUuid, 1L);
    }
}
