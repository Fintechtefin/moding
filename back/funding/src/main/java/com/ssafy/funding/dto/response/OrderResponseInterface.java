package com.ssafy.funding.dto.response;

import com.ssafy.funding.repository.OrderRepository;
import lombok.Builder;

@Builder
public class OrderResponseInterface implements OrderRepository.OrderResponseInterface {

    private Integer count;

    @Override
    public Integer getCount() {
        return count;
    }
}
