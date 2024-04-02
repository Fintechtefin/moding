package com.ssafy.funding.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.funding.controller.feign.TokenAuthClient;
import com.ssafy.funding.dto.request.ConfirmOrderRequest;
import com.ssafy.funding.dto.response.OrderConfirmResponse;
import com.ssafy.funding.service.MockService;
import com.ssafy.funding.service.OrderService;
import com.ssafy.funding.service.client.UserClient;
import io.github.bucket4j.Bucket;
import java.util.UUID;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@AutoConfigureDataJpa
@WebMvcTest(controllers = OrderControllerTest.class)
public class OrderControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private OrderService orderService;
    @MockBean private MockService mockService;
    @MockBean private UserClient userClient;
    @MockBean private TokenAuthClient tokenAuthClient;
    @MockBean private Bucket bucket;

    /*
    404가 뜬다.
    작성 중
     */
    @Test
    @Disabled
    @DisplayName("결제 승인")
    void TestConfirmOrder() throws Exception {
        // given
        ConfirmOrderRequest ConfirmOrderRequest =
                com.ssafy.funding.dto.request.ConfirmOrderRequest.builder()
                        .paymentKey(UUID.randomUUID().toString())
                        .amount(10000L)
                        .build();
        String orderUuid = "MC4wODU4ODQwMzg4NDk0";
        given(orderService.confirmOrder(anyString(), any(), anyInt()))
                .willReturn(OrderConfirmResponse.builder().build());

        for (int i = 0; i < 100; i++) {
            ResultActions actions =
                    mockMvc.perform(
                            post("/fundings/" + orderUuid + "/confirm")
                                    .content(objectMapper.writeValueAsString(ConfirmOrderRequest)));
        }

        mockMvc.perform(
                        post("/fundings/" + orderUuid + "/confirm")
                                .content(objectMapper.writeValueAsString(ConfirmOrderRequest)))
                .andExpect(status().isTooManyRequests());
    }
}
