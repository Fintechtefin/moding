package com.ssafy.funding.mapper;

import com.ssafy.common.dto.request.ConfirmPaymentsRequest;
import com.ssafy.funding.dto.request.ConfirmOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "confirmOrderRequest.paymentKey", target = "paymentKey")
    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source = "id", target = "id")
    ConfirmPaymentsRequest toConfirmPaymentsRequest(
            ConfirmOrderRequest confirmOrderRequest, String orderId, Long id);
}
