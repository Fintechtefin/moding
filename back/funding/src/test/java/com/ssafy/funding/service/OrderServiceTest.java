package com.ssafy.funding.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

import com.ssafy.funding.controller.feign.CreatePaymentClient;
import com.ssafy.funding.domain.Funding;
import com.ssafy.funding.domain.Movie;
import com.ssafy.funding.domain.validator.OrderValidator;
import com.ssafy.funding.dto.response.OrderConfirmResponse;
import com.ssafy.funding.messaging.Producer;
import com.ssafy.funding.repository.FundingRepository;
import com.ssafy.funding.repository.OrderRepository;
import com.ssafy.funding.service.client.PaymentClient;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks private OrderService orderService;
    @Mock private OrderRepository orderRepository;
    @Mock private FundingRepository fundingRepository;
    @Mock private OrderValidator orderValidator;
    @Mock private PaymentClient paymentClient;
    @Mock private CreatePaymentClient createPaymentClient; // feignClient
    @Mock private Producer producer;

    // @Test
    @DisplayName("펀딩을 한 번 참여했을 때 결제 승인을 테스트한다.")
    void confirmOrderTest() {
        // given
        Funding funding =
                Funding.builder()
                        .id(1)
                        .peopleCount(100)
                        .price(10000)
                        .movie(Movie.builder().build())
                        .startAt(LocalDateTime.now())
                        .build();
        /*ConfirmOrderRequest confirmOrderRequest = ConfirmOrderRequest.builder()
        .paymentKey("5EnNZRJGvaBX7zk2yd8ydw26XvwXkLrx9POLqKQjmAw4b0e1")
        .amount(10000L)
        .fundingId(1)
        .fundingCount(1)
        .method("카드")
        .build();*/
        given(fundingRepository.findById(anyInt())).willReturn(Optional.ofNullable(funding));

        // when
        OrderConfirmResponse orderConfirmResponse =
                orderService.confirmOrder("MC4wODU4ODQwMzg4NDk0", any(), 1);

        // then
        assertThat(orderConfirmResponse.getAmount()).isEqualTo(funding.getPrice());
    }
}
