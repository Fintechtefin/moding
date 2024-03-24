package com.ssafy.payment.repository;

import com.ssafy.payment.domain.PaymentStatus;
import org.springframework.data.repository.CrudRepository;

public interface PaymentStatusRepository extends CrudRepository<PaymentStatus, Integer> {
    PaymentStatus findByName(String name);
}
