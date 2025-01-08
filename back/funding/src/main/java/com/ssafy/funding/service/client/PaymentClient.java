package com.ssafy.funding.service.client;

import com.ssafy.common.dto.request.ConfirmPaymentsRequest;
import com.ssafy.common.dto.request.CreatePaymentsRequest;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.examples.lib.*;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentClient {

    @GrpcClient("payment")
    OrderServiceGrpc.OrderServiceBlockingStub orderStub;

    public CallTossPayResponse callTossPay(CreatePaymentsRequest createPaymentsRequest) {
        return orderStub.callTossPay(
                CallTossPayRequest.newBuilder()
                        .setMethod(createPaymentsRequest.getMethod())
                        .setAmount(createPaymentsRequest.getAmount())
                        .setOrderId(createPaymentsRequest.getOrderId())
                        .setOrderName(createPaymentsRequest.getOrderName())
                        .setSuccessUrl(createPaymentsRequest.getSuccessUrl())
                        .setFailUrl(createPaymentsRequest.getFailUrl())
                        .build());
    }

    public CallTossPayConfirmResponse callTossPayConfirm(
            ConfirmPaymentsRequest confirmPaymentsRequest, Long id) {
        return orderStub.callTossPayConfirm(
                CallTossPayConfirmRequest.newBuilder()
                        .setAmount(confirmPaymentsRequest.getAmount())
                        .setOrderId(confirmPaymentsRequest.getOrderId())
                        .setPaymentKey(confirmPaymentsRequest.getPaymentKey())
                        .setId(id)
                        .build());
    }
}
