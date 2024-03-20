package com.ssafy.funding.service;

import static com.ssafy.funding.exception.global.CustomExceptionStatus.FUNDING_NOT_FOUND;

import com.ssafy.funding.domain.Funding;
import com.ssafy.funding.domain.Order;
import com.ssafy.funding.domain.OrderFunding;
import com.ssafy.funding.domain.validator.OrderValidator;
import com.ssafy.funding.dto.request.CreateOrderRequest;
import com.ssafy.funding.dto.response.OrderResponse;
import com.ssafy.funding.exception.BadRequestException;
import com.ssafy.funding.repository.FundingRepository;
import com.ssafy.funding.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
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

    public OrderResponse orderFunding(
            final CreateOrderRequest createOrderRequest, final Integer currentUserId) {
        final Order order = createFundingOrder(createOrderRequest, currentUserId);
        order.confirm(currentUserId, orderValidator);
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
        List<OrderFunding> orderFundings = new ArrayList<OrderFunding>();
        orderFundings.add(
                OrderFunding.createOrderFunding(
                        funding.getPrice(), createOrderRequest.getFundingCount(), funding, order));
        order.addOrderFundings(orderFundings, funding, createOrderRequest.getFundingCount());
        return orderRepository.save(order);
    }
}
