package com.ssafy.funding.validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ssafy.funding.domain.Order;
import com.ssafy.funding.domain.validator.OrderValidator;
import com.ssafy.funding.exception.BadRequestException;
import com.ssafy.funding.exception.global.CustomExceptionStatus;
import com.ssafy.funding.repository.FundingRepository;
import com.ssafy.funding.repository.MovieRepository;
import com.ssafy.funding.repository.OrderRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderValidatorTest {
    @InjectMocks private OrderValidator orderValidator;
    @Mock private FundingRepository fundingRepository;
    @Mock private MovieRepository movieRepository;
    @Mock private OrderRepository orderRepository;

    @Test
    @DisplayName("유저의 단일 주문 검증 - 기존 주문이 있을 경우 예외 발생")
    void testValidOnlyOneOrder_NoOrder() {
        // given
        int userId = 1;
        int fundingId = 123;

        when(orderRepository.findByUserIdAndFundingIdAndStatus(userId, fundingId, true))
                .thenReturn(Optional.ofNullable(Order.builder().build()));

        // when, then
        BadRequestException exception =
                assertThrows(
                        BadRequestException.class,
                        () -> {
                            orderValidator.validOnlyOneOrder(userId, fundingId);
                        });
        assertEquals(CustomExceptionStatus.ORDER_FORBIDDEN.getMessage(), exception.getMessage());
        verify(orderRepository, times(1))
                .findByUserIdAndFundingIdAndStatus(userId, fundingId, true);
    }

    @Test
    @DisplayName("유저의 단일 주문 검증 - 기존 주문이 없는 경우 성공")
    void testValidOnlyOneOrder_WithOrder() {
        // given
        int userId = 1;
        int fundingId = 123;

        when(orderRepository.findByUserIdAndFundingIdAndStatus(userId, fundingId, true))
                .thenReturn(Optional.empty());

        // when, then
        assertDoesNotThrow(
                () -> {
                    orderValidator.validOnlyOneOrder(userId, fundingId);
                });
        verify(orderRepository, times(1))
                .findByUserIdAndFundingIdAndStatus(userId, fundingId, true);
    }
}
