package com.ssafy.funding.controller;

import com.ssafy.common.dto.request.CreatePaymentsRequest;
import com.ssafy.funding.controller.feign.TokenAuthClient;
import com.ssafy.funding.dto.request.ConfirmOrderRequest;
import com.ssafy.funding.dto.request.RefundOrderRequest;
import com.ssafy.funding.service.MockService;
import com.ssafy.funding.service.OrderService;
import com.ssafy.funding.service.client.UserClient;
import io.github.bucket4j.Bucket;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
// @CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;
    private final MockService mockService;
    private final UserClient userClient;
    private final TokenAuthClient tokenAuthClient;
    private final Bucket bucket;

    @Operation(summary = "주문(펀딩)을 결제합니다.")
    @PostMapping
    public ResponseEntity<?> orderFunding(
            @RequestBody CreatePaymentsRequest createPaymentsRequest) {
        return ResponseEntity.ok(orderService.requestPayment(createPaymentsRequest));
    }

    @Operation(summary = "결제 확인하기. successUrl 로 돌아온 웹페이지에서 query 로 받은 응답값을 서버로 보내주세요.")
    @PostMapping("/{order_uuid}/confirm")
    public ResponseEntity<?> confirmOrder(
            @RequestHeader("Authorization") String authorization,
            @PathVariable("order_uuid") String orderUuid,
            @RequestBody ConfirmOrderRequest confirmOrderRequest) {

        // API 호출 시 토큰 1개 소비
        if (!bucket.tryConsume(1)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }

        return ResponseEntity.ok(
                orderService.confirmOrder(
                        orderUuid, confirmOrderRequest, tokenAuthClient.getUserId(authorization)));
    }

    @Operation(
            summary =
                    "[성능 테스트] MockServer에 결제 확인하기. successUrl 로 돌아온 웹페이지에서 query 로 받은 응답값을 서버로 보내주세요.")
    @PostMapping("/test/{order_uuid}/confirm")
    public ResponseEntity<?> confirmOrderToMockServer(
            @RequestHeader("Authorization") String authorization,
            @PathVariable("order_uuid") String orderUuid,
            @RequestBody ConfirmOrderRequest confirmOrderRequest) {
        return ResponseEntity.ok(
                mockService.confirmOrder(
                        orderUuid, confirmOrderRequest, tokenAuthClient.getUserId(authorization)));
    }

    @Operation(summary = "결제 환불요청. 본인이 구매한 오더를 환불 시킵니다.! (본인 용)")
    @PostMapping("/{order_uuid}/refund")
    public ResponseEntity<?> refundOrder(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable("order_uuid") String orderUuid,
            @RequestBody RefundOrderRequest refundOrderRequest) {
        orderService.refundFunding(
                tokenAuthClient.getUserId(accessToken), orderUuid, refundOrderRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "펀딩 결제 여부를 확인합니다.")
    @GetMapping("/check/{fundingId}/{userId}")
    public ResponseEntity<?> checkPaymentUser(
            @PathVariable Integer fundingId, @PathVariable Integer userId) {
        return ResponseEntity.ok(orderService.checkPaymentUser(fundingId, userId));
    }
}
