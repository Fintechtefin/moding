package com.ssafy.funding.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.ssafy.funding.controller.feign.TokenAuthClient;
import com.ssafy.funding.domain.Funding;
import com.ssafy.funding.domain.Movie;
import com.ssafy.funding.domain.Order;
import com.ssafy.funding.dto.response.JoinFundingListResponse;
import com.ssafy.funding.repository.FundingRepository;
import com.ssafy.funding.repository.MovieFundingRepository;
import com.ssafy.funding.repository.MovieRepository;
import com.ssafy.funding.repository.OrderRepository;
import com.ssafy.funding.util.RedisUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@ExtendWith(MockitoExtension.class)
public class FundingServiceTest {

    @InjectMocks private FundingService fundingService;
    @Mock private FundingRepository fundingRepository;
    @Mock private TokenAuthClient tokenAuthClient;
    @Mock private MovieFundingRepository movieFundingRepository;
    @Mock private MovieRepository movieRepository;
    @Mock private OrderRepository orderRepository;
    @Mock private RedisUtil redisUtil;

    @Test
    @Disabled
    @DisplayName("내가 참여한 진행 중인 펀딩 목록을 보여줍니다. - 결제를 한 펀딩 조회")
    void TestGetMyFundingsFilterPayment() {
        // given
        given(tokenAuthClient.getUserId(anyString())).willReturn(1);
        List<Order> orderList = new ArrayList<>();
        /*
        결제 완료한 주문
         */
        orderList.add(
                Order.builder()
                        .id(1L)
                        .funding(
                                Funding.builder()
                                        .id(1)
                                        .startAt(LocalDateTime.now())
                                        .movie(Movie.builder().id(1).build())
                                        .build())
                        .userId(1)
                        .uuid("abcd234")
                        .price(10000)
                        .status(true)
                        .build());
        /*
        결제를 취소한 주문
         */
        orderList.add(
                Order.builder()
                        .id(1L)
                        .funding(
                                Funding.builder()
                                        .id(1)
                                        .startAt(LocalDateTime.now())
                                        .movie(Movie.builder().id(1).build())
                                        .build())
                        .userId(1)
                        .uuid("abcd234")
                        .price(10000)
                        .status(false)
                        .build());
        Slice<Order> orders = new SliceImpl<>(orderList);
        given(orderRepository.findByUserId(anyInt())).willReturn(orders);

        // when
        JoinFundingListResponse result = fundingService.getMyFundings(anyString());

        // then
        assertNotNull(result);
        assertEquals(result.getJoinFundingResponseList().size(), 1);
    }

    @Test
    @Disabled
    @DisplayName("내가 참여한 진행 중인 펀딩 목록을 보여줍니다. - 진행 중인 펀딩 조회")
    void TestGetMyFundingsFilterFundingValid() {
        // given
        given(tokenAuthClient.getUserId(anyString())).willReturn(1);
        List<Order> orderList = new ArrayList<>();
        /*
        진행 중인 펀딩 주문건
         */
        orderList.add(
                Order.builder()
                        .id(1L)
                        .funding(
                                Funding.builder()
                                        .id(1)
                                        .startAt(LocalDateTime.now())
                                        .movie(Movie.builder().id(1).build())
                                        .build())
                        .userId(1)
                        .uuid("abcd234")
                        .price(10000)
                        .status(true)
                        .build());
        /*
        종료된 펀딩 주문건
         */
        orderList.add(
                Order.builder()
                        .id(1L)
                        .funding(
                                Funding.builder()
                                        .id(1)
                                        .startAt(LocalDateTime.now().plusDays(20))
                                        .movie(Movie.builder().id(1).build())
                                        .build())
                        .userId(1)
                        .uuid("abcd234")
                        .price(10000)
                        .status(false)
                        .build());
        Slice<Order> orders = new SliceImpl<>(orderList);
        given(orderRepository.findByUserId(anyInt())).willReturn(orders);

        // when
        JoinFundingListResponse result = fundingService.getMyFundings(anyString());

        // then
        assertNotNull(result);
        assertEquals(result.getJoinFundingResponseList().size(), 1);
    }
}
