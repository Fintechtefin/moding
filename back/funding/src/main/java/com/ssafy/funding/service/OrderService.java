package com.ssafy.funding.service;

import static com.ssafy.funding.exception.global.CustomExceptionStatus.FUNDING_NOT_FOUND;
import static com.ssafy.funding.exception.global.CustomExceptionStatus.ORDER_NOT_FOUND;

import com.ssafy.funding.domain.Funding;
import com.ssafy.funding.domain.Order;
import com.ssafy.funding.domain.PaymentMethod;
import com.ssafy.funding.domain.validator.OrderValidator;
import com.ssafy.funding.dto.Money;
import com.ssafy.funding.dto.request.ConfirmOrderRequest;
import com.ssafy.funding.dto.request.ConfirmPaymentsRequest;
import com.ssafy.funding.dto.request.CreateOrderRequest;
import com.ssafy.funding.dto.request.CreatePaymentsRequest;
import com.ssafy.funding.dto.response.OrderConfirmResponse;
import com.ssafy.funding.dto.response.OrderResponse;
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
// @Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final FundingRepository fundingRepository;
    private final OrderValidator orderValidator;
    private final PaymentClient paymentClient;
    // private final Producer producer;

    // @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    // @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OrderResponse orderFunding(final CreateOrderRequest createOrderRequest, Integer userId) {
        final Order order = createFundingOrder(createOrderRequest, userId);
        order.confirm(1, orderValidator);
        paymentClient.callTossPay(
                CreatePaymentsRequest.builder()
                        .id(order.getId())
                        .orderId(order.getUuid()) // 주문 번호
                        .method(PaymentMethod.of(createOrderRequest.getMethod()).getMethod())
                        .amount(
                                Long.valueOf(
                                        order.getCount() * order.getPrice())) // 단일 펀딩을 다수 개 주문 가능
                        .orderName("주문")
                        .successUrl("http://localhost:8082")
                        .failUrl("http://localhost:8082")
                        .build());
        // producer.sendCreateOrderRequest("order", order);
        return OrderResponse.of(order);
    }

    public Order createFundingOrder(
            final CreateOrderRequest createOrderRequest, final Integer currentUserId) {
        final Funding funding =
                fundingRepository
                        .findById(createOrderRequest.getFundingId())
                        .orElseThrow(() -> new BadRequestException(FUNDING_NOT_FOUND));

        final Order order =
                Order.createPaymentOrder(
                        currentUserId,
                        funding,
                        createOrderRequest.getFundingCount(),
                        funding.getPrice(),
                        orderValidator);

        return orderRepository.save(order);
    }

    public OrderConfirmResponse confirmOrder(
            String orderUuid, ConfirmOrderRequest confirmOrderRequest, Integer userId) {
        ConfirmPaymentsRequest confirmPaymentsRequest =
                ConfirmPaymentsRequest.builder()
                        .paymentKey(confirmOrderRequest.getPaymentKey())
                        .amount(confirmOrderRequest.getAmount())
                        .orderId(orderUuid)
                        .build();

        Order order =
                orderRepository
                        .findByUuid(confirmPaymentsRequest.getOrderId())
                        .orElseThrow(() -> new BadRequestException(ORDER_NOT_FOUND));
        Money paymentWons = Money.wons(confirmPaymentsRequest.getAmount());

        orderValidator.validOwner(order, userId);
        orderValidator.validMethodIsPaymentOrder(order);
        orderValidator.validAmountIsSameAsRequest(order, paymentWons);

        paymentClient.callTossPayConfirm(confirmPaymentsRequest, order.getId());

        return OrderConfirmResponse.of(order);
    }
}
