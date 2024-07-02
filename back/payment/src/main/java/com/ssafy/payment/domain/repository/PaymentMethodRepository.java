package com.ssafy.payment.domain.repository;

import com.ssafy.payment.domain.PaymentMethod;
import org.springframework.data.repository.CrudRepository;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, Integer> {
    PaymentMethod findByName(String name);
}
