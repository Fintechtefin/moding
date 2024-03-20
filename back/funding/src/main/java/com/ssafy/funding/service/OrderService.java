package com.ssafy.funding.service;

import static com.ssafy.funding.exception.global.CustomExceptionStatus.ORDER_NOT_FOUND;

import com.ssafy.funding.domain.Order;
import com.ssafy.funding.domain.validator.OrderValidator;
import com.ssafy.funding.dto.response.OrderResponse;
import com.ssafy.funding.exception.BadRequestException;
import com.ssafy.funding.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;

    public OrderResponse orderFunding(String orderUuid, Long currentUserId) {
        Order order =
                orderRepository
                        .findByUuid(orderUuid)
                        .orElseThrow(() -> new BadRequestException(ORDER_NOT_FOUND));
        order.confirm(currentUserId, orderValidator);
        return OrderResponse.of(order);
    }
}
