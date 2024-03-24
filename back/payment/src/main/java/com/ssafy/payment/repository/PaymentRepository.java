package com.ssafy.payment.repository;

import com.ssafy.payment.domain.Payment;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
    Optional<Payment> findByPaymentKey(String paymentKey);
}
