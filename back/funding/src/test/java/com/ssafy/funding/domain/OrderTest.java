package com.ssafy.funding.domain;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.ssafy.funding.domain.validator.OrderValidator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderTest {
    @Mock OrderValidator orderValidator;

    @Test
    @Disabled
    @DisplayName("주문 생성 성공")
    public void TestCreatePaymentOrderSuccess() {
        // given
        Integer userId = 1;
        Funding funding = Funding.builder().id(1).build();
        Integer fundingCount = 10;
        Integer fundingPrice = 10000;
        Long amount = Long.valueOf(fundingCount * fundingPrice);

        // when
        Order.createPaymentOrder(
                userId, funding, fundingCount, fundingPrice, amount, orderValidator);

        // then
        verify(orderValidator).validOnlyOneOrder(userId, funding.getId());
        verify(orderValidator).validFundingIsOpen(funding);
        verify(orderValidator).validFundingStockEnough(any(Order.class), any(Funding.class));
    }
}
