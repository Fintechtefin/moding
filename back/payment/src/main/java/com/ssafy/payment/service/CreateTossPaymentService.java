package com.ssafy.payment.service;

import com.ssafy.payment.controller.PaymentsConfirmClient;
import com.ssafy.payment.controller.PaymentsCreateClient;
import com.ssafy.payment.domain.Payment;
import com.ssafy.payment.domain.PaymentMethod;
import com.ssafy.payment.domain.PaymentStatus;
import com.ssafy.payment.domain.repository.PaymentCancelRepository;
import com.ssafy.payment.dto.request.ConfirmPaymentsRequest;
import com.ssafy.payment.dto.request.CreatePaymentsRequest;
import com.ssafy.payment.dto.response.PaymentsResponse;
import com.ssafy.payment.exception.NotFoundPaymentException;
import com.ssafy.payment.domain.repository.PaymentMethodRepository;
import com.ssafy.payment.domain.repository.PaymentRepository;
import com.ssafy.payment.domain.repository.PaymentStatusRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.examples.lib.*;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@GrpcService
@RequiredArgsConstructor
@Transactional
public class CreateTossPaymentService extends OrderServiceGrpc.OrderServiceImplBase {
    private final PaymentsConfirmClient paymentsConfirmClient;
    private final PaymentsCreateClient paymentsCreateClient;
    private final PaymentCancelRepository paymentMethodRepository;
    private final PaymentCancelRepository paymentRepository;
    private final PaymentCancelRepository paymentStatusRepository;

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

        PaymentMethod paymentMethod = paymentMethodRepository.findByName(request.getMethod());
        PaymentStatus paymentStatus =
                paymentStatusRepository.findByName(paymentsResponse.getStatus().name());

        paymentRepository.save(
                Payment.createPayment(
                        request.getId(), paymentsResponse, paymentMethod, paymentStatus));

        responseObserver.onNext(
                CallTossPayResponse.newBuilder()
                        .setPaymentKey(paymentsResponse.getPaymentKey())
                        .build());
        responseObserver.onCompleted();
    }

    @Override
    public void callTossPayConfirm(
            CallTossPayConfirmRequest request,
            StreamObserver<CallTossPayConfirmResponse> responseObserver) {
        PaymentsResponse paymentsResponse =
                paymentsConfirmClient.execute(
                        ConfirmPaymentsRequest.builder()
                                .paymentKey(request.getPaymentKey())
                                .orderId(request.getOrderId())
                                .amount(request.getAmount())
                                .build());

        Payment payment =
                paymentRepository
                        .findByPaymentKey(request.getPaymentKey())
                        .orElseThrow(() -> NotFoundPaymentException.EXCEPTION);
        PaymentMethod paymentMethod = paymentMethodRepository.findByName(payment.getPaymentKey());
        PaymentStatus paymentStatus =
                paymentStatusRepository.findByName(paymentsResponse.getStatus().name());
        paymentRepository.save(
                Payment.createPayment(
                        request.getId(), paymentsResponse, paymentMethod, paymentStatus));

        responseObserver.onNext(
                CallTossPayConfirmResponse.newBuilder().setOrderId(request.getOrderId()).build());
        responseObserver.onCompleted();
    }
}
