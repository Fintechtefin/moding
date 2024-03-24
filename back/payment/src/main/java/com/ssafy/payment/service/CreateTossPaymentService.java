package com.ssafy.payment.service;

import com.ssafy.payment.controller.PaymentsCreateClient;
import com.ssafy.payment.dto.request.CreatePaymentsRequest;
import com.ssafy.payment.dto.response.PaymentsResponse;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.examples.lib.CallTossPayRequest;
import net.devh.boot.grpc.examples.lib.CallTossPayResponse;
import net.devh.boot.grpc.examples.lib.OrderServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@GrpcService
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreateTossPaymentService extends OrderServiceGrpc.OrderServiceImplBase {

    private final PaymentsCreateClient paymentsCreateClient;

    @Override
    public void callTossPay(
            CallTossPayRequest request, StreamObserver<CallTossPayResponse> responseObserver) {
        PaymentsResponse paymentsResponse =
                paymentsCreateClient.execute(
                        CreatePaymentsRequest.builder()
                                .method(request.getMethod())
                                .amount(request.getAmount())
                                .orderId(request.getOrderId())
                                .orderName(request.getOrderName())
                                .successUrl(request.getSuccessUrl())
                                .failUrl(request.getFailUrl())
                                .build());

        responseObserver.onNext(
                CallTossPayResponse.newBuilder()
                        .setPaymentKey(paymentsResponse.getPaymentKey())
                        .build());
        responseObserver.onCompleted();
    }
}
