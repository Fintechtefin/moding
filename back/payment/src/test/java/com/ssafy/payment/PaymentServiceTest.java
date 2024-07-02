package com.ssafy.payment;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import com.ssafy.payment.controller.PaymentsCancelClient;
import com.ssafy.payment.controller.PaymentsConfirmClient;
import com.ssafy.payment.domain.Payment;
import com.ssafy.payment.domain.PaymentMethod;
import com.ssafy.payment.domain.PaymentStatus;
import com.ssafy.payment.domain.repository.PaymentCancelRepository;
import com.ssafy.payment.domain.repository.PaymentMethodRepository;
import com.ssafy.payment.domain.repository.PaymentRepository;
import com.ssafy.payment.domain.repository.PaymentStatusRepository;
import com.ssafy.payment.dto.request.ConfirmPaymentsRequest;
import com.ssafy.payment.dto.request.RefundOrderRequest;
import com.ssafy.payment.dto.response.PaymentsResponse;
import com.ssafy.payment.service.PaymentService;
import java.time.ZonedDateTime;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @InjectMocks private PaymentService paymentService;
    @Mock private PaymentsConfirmClient paymentsConfirmClient;
    @Mock private PaymentsCancelClient paymentsCancelClient;
    @Mock private PaymentCancelRepository paymentCancelRepository;
    @Mock private PaymentRepository paymentRepository;
    @Mock private PaymentStatusRepository paymentStatusRepository;
    @Mock private PaymentMethodRepository paymentMethodRepository;

    @Test
    @DisplayName("토스페이먼츠 결제 승인")
    void testCallTossPayConfirm() {
        // given
        given(paymentsConfirmClient.execute(any(ConfirmPaymentsRequest.class)))
                .willReturn(PaymentsResponse.builder().build());
        given(paymentMethodRepository.findById(1))
                .willReturn(
                        Optional.ofNullable(PaymentMethod.builder().id(1).name("DONE").build()));
        given(paymentStatusRepository.findById(4))
                .willReturn(Optional.ofNullable(PaymentStatus.builder().id(4).name("카드").build()));

        // when
        paymentService.callTossPayConfirm(
                ConfirmPaymentsRequest.builder()
                        .paymentKey("wefwe324234")
                        .orderId("fwew342")
                        .amount(10000L)
                        .build());

        // then
        verify(paymentRepository).save(any(Payment.class));
    }

    @Test
    @DisplayName("토스페이먼츠 환불")
    void testCallTossPayRefund() {
        // given
        RefundOrderRequest refundOrderRequest =
                RefundOrderRequest.builder().orderId(1L).cancelReason("단순변심").build();

        Payment mockPayment = Payment.builder().paymentKey("aeijgwhhtio23").build();

        given(paymentRepository.findByOrderId(anyLong())).willReturn(Optional.of(mockPayment));

        PaymentsResponse mockPaymentsResponse =
                PaymentsResponse.builder()
                        .approvedAt(ZonedDateTime.now())
                        .requestedAt(ZonedDateTime.now())
                        .totalAmount(10000L)
                        .build();

        given(paymentsCancelClient.execute(anyString(), anyString(), any()))
                .willReturn(mockPaymentsResponse);

        given(paymentStatusRepository.findById(anyInt()))
                .willReturn(
                        Optional.ofNullable(
                                PaymentStatus.builder().id(5).name("CANCELED").build()));

        // when
        paymentService.callTossPayRefund(refundOrderRequest);

        // then
        verify(paymentRepository, times(1)).findByOrderId(anyLong());
        verify(paymentsCancelClient, times(1)).execute(anyString(), anyString(), any());
        verify(paymentCancelRepository, times(1)).save(any());
        verify(paymentStatusRepository, times(1)).findById(anyInt());
    }
}
