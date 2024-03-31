package com.ssafy.toss.controller;

import com.ssafy.toss.dto.ConfirmPaymentsRequest;
import com.ssafy.toss.service.TossPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TossPaymentController {

    private final TossPaymentService tossPaymentService;

    @PostMapping("/v1/payments.confirm")
    public ResponseEntity<?> tossPaymentConfirm(
            @RequestBody ConfirmPaymentsRequest confirmPaymentsRequest) {
        return ResponseEntity.ok(tossPaymentService);
    }
}
