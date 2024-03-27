package com.ssafy.payment.controller;

import com.ssafy.payment.dto.request.ConfirmPaymentsRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentsConfirmClient paymentsConfirmClient;

    //    @PostMapping
    //    @Operation(summary = "백엔드 내부에서 토스페이먼츠 결제 승인 API 호출")
    //    public ResponseEntity<?> callConfirmAPI(
    //            @RequestBody ConfirmPaymentsRequest confirmPaymentsRequest) {
    //        //paymentsConfirmClient.execute(confirmPaymentsRequest);
    //        testService.call(confirmPaymentsRequest);
    //        return ResponseEntity.ok().build();
    //    }

    @PostMapping("/confirm")
    @Operation(summary = "백엔드 내부에서 토스페이먼츠 결제 승인 API 호출")
    public ResponseEntity<?> callConfirmAPI(
            @RequestBody ConfirmPaymentsRequest confirmPaymentsRequest) {
        paymentsConfirmClient.execute(confirmPaymentsRequest);
        return ResponseEntity.ok().build();
    }
}
