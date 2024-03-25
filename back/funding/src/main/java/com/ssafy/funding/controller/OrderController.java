package com.ssafy.funding.controller;

import com.ssafy.funding.dto.request.ConfirmOrderRequest;
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

    //    @Operation(summary = "주문(펀딩)을 결제합니다.")
    //    @PostMapping
    //    public ResponseEntity<?> orderFunding(@RequestBody CreateOrderRequest createOrderRequest)
    // {
    //        return ResponseEntity.ok(
    //                orderService.orderFunding(
    //                        createOrderRequest,
    //                        Integer.parseInt(userClient.findUserInfo().getUsername())));
    //    }

    @Operation(summary = "결제 확인하기. successUrl 로 돌아온 웹페이지에서 query 로 받은 응답값을 서버로 보내주세요.")
    @PostMapping("/{order_uuid}/confirm")
    public ResponseEntity<?> confirmOrder(
            @PathVariable("order_uuid") String orderUuid,
            @RequestBody ConfirmOrderRequest confirmOrderRequest) {
        return ResponseEntity.ok(
                orderService.confirmOrder(
                        orderUuid,
                        confirmOrderRequest,
                        Integer.parseInt(userClient.findUserInfo().getUsername())));
    }
}
