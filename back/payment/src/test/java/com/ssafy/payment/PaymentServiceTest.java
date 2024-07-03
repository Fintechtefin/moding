package com.ssafy.payment;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import com.ssafy.payment.controller.PaymentsCancelClient;
import com.ssafy.payment.controller.PaymentsConfirmClient;
import com.ssafy.payment.domain.Payment;
import com.ssafy.payment.domain.PaymentMethod;
import com.ssafy.payment.domain.PaymentStatus;
import com.ssafy.payment.dto.request.ConfirmPaymentsRequest;
import com.ssafy.payment.dto.request.RefundOrderRequest;
import com.ssafy.payment.dto.response.PaymentsResponse;
import com.ssafy.payment.service.PaymentFacadeRepository;
import com.ssafy.payment.service.PaymentService;
import java.time.ZonedDateTime;
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
    @Mock private PaymentFacadeRepository paymentFacadeRepository;

    @Test
    @DisplayName("토스페이먼츠 결제 승인")
    void testCallTossPayConfirm() {
        // given
        given(paymentsConfirmClient.execute(any(ConfirmPaymentsRequest.class)))
                .willReturn(PaymentsResponse.builder().build());
        given(paymentFacadeRepository.findPaymentMethod())
                .willReturn(PaymentMethod.builder().id(1).name("DONE").build());
        given(paymentFacadeRepository.findPaymentStatus())
                .willReturn(PaymentStatus.builder().id(4).name("카드").build());

        // when
        paymentService.callTossPayConfirm(
                ConfirmPaymentsRequest.builder()
                        .paymentKey("wefwe324234")
                        .orderId("fwew342")
                        .amount(10000L)
                        .build());

        // then
        verify(paymentFacadeRepository).savePayment(any(Payment.class));
    }

    @Test
    @DisplayName("토스페이먼츠 환불")
    void testCallTossPayRefund() {
        // given
        RefundOrderRequest refundOrderRequest =
                RefundOrderRequest.builder().orderId(1L).cancelReason("단순변심").build();

        Payment mockPayment = Payment.builder().paymentKey("aeijgwhhtio23").build();

        given(paymentFacadeRepository.findPaymentByOrderId(anyLong())).willReturn(mockPayment);

        PaymentsResponse mockPaymentsResponse =
                PaymentsResponse.builder()
                        .approvedAt(ZonedDateTime.now())
                        .requestedAt(ZonedDateTime.now())
                        .totalAmount(10000L)
                        .build();

        given(paymentsCancelClient.execute(anyString(), anyString(), any()))
                .willReturn(mockPaymentsResponse);

        given(paymentFacadeRepository.findPaymentStatusById())
                .willReturn(PaymentStatus.builder().id(5).name("CANCELED").build());

        // when
        paymentService.callTossPayRefund(refundOrderRequest);

        // then
        verify(paymentFacadeRepository, times(1)).findPaymentByOrderId(anyLong());
        verify(paymentsCancelClient, times(1)).execute(anyString(), anyString(), any());
        verify(paymentFacadeRepository, times(1)).savePaymentCancel(any(), any());
        verify(paymentFacadeRepository, times(1)).findPaymentStatusById();
    }
}
