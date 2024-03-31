package com.ssafy.toss.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.toss.dto.ConfirmPaymentsRequest;
import com.ssafy.toss.service.TossPaymentService;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(controllers = TossPaymentController.class)
public class TossPaymentControllerTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private ObjectMapper objectMapper;

    @MockBean private TossPaymentService tossPaymentService;

    @Test
    @DisplayName("결제 승인")
    void TestTossPaymentConfirm() throws Exception {
        // given
        ConfirmPaymentsRequest confirmPaymentsRequest =
                ConfirmPaymentsRequest.builder()
                        .paymentKey(UUID.randomUUID().toString())
                        .orderId(UUID.randomUUID().toString())
                        .amount(10000L)
                        .id(1L)
                        .build();
        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/v1/payments.confirm")
                                .content(objectMapper.writeValueAsString(confirmPaymentsRequest)));

        // then
        actions.andDo(print());
    }
}
