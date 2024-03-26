package com.ssafy.funding.service;

import static com.ssafy.funding.exception.global.CustomExceptionStatus.FUNDING_NOT_FOUND;

import com.ssafy.funding.domain.Funding;
import com.ssafy.funding.domain.Order;
import com.ssafy.funding.domain.validator.OrderValidator;
import com.ssafy.funding.dto.Money;
import com.ssafy.funding.dto.request.ConfirmOrderRequest;
import com.ssafy.funding.dto.request.ConfirmPaymentsRequest;
import com.ssafy.funding.dto.request.CreatePaymentsRequest;
import com.ssafy.funding.dto.response.OrderConfirmResponse;
import com.ssafy.funding.exception.BadRequestException;
import com.ssafy.funding.repository.FundingRepository;
import com.ssafy.funding.repository.OrderRepository;
import com.ssafy.funding.service.client.PaymentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final FundingRepository fundingRepository;
    private final OrderValidator orderValidator;
    private final PaymentClient paymentClient;

    public String requestPayment(CreatePaymentsRequest createPaymentsRequest) {
        return paymentClient.callTossPay(createPaymentsRequest).getPaymentKey();
    }

    public Order orderFunding(final ConfirmOrderRequest confirmOrderRequest, Integer userId) {
        final Order order = createFundingOrder(confirmOrderRequest, userId);
        order.confirm(1, orderValidator);
        return order;
    }

    public Order createFundingOrder(
            final ConfirmOrderRequest confirmOrderRequest, final Integer currentUserId) {
        final Funding funding =
                fundingRepository
                        .findById(confirmOrderRequest.getFundingId())
                        .orElseThrow(() -> new BadRequestException(FUNDING_NOT_FOUND));

        final Order order =
                Order.createPaymentOrder(
                        currentUserId,
                        funding,
                        confirmOrderRequest.getFundingCount(),
                        funding.getPrice(),
                        orderValidator);

        return orderRepository.save(order);
    }

    /*
    결제 승인
     */
    public OrderConfirmResponse confirmOrder(
            String orderUuid, ConfirmOrderRequest confirmOrderRequest, Integer userId) {

        Order order = orderFunding(confirmOrderRequest, userId); // 주문 생성

        ConfirmPaymentsRequest confirmPaymentsRequest =
                ConfirmPaymentsRequest.builder()
                        .paymentKey(confirmOrderRequest.getPaymentKey())
                        .amount(confirmOrderRequest.getAmount())
                        .orderId(orderUuid)
                        .build();

        Money paymentWons = Money.wons(confirmPaymentsRequest.getAmount());

        // orderValidator.validOwner(order, userId);
        // orderValidator.validMethodIsPaymentOrder(order);
        // orderValidator.validAmountIsSameAsRequest(order, paymentWons);

        paymentClient.callTossPayConfirm(confirmPaymentsRequest, order.getId());

        return OrderConfirmResponse.of(order);
    }
}
