package com.ssafy.funding.repository;

import com.ssafy.funding.domain.Order;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

    Optional<Order> findByUuid(String orderUuid);
}
