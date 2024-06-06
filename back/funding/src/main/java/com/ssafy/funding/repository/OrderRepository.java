package com.ssafy.funding.repository;

import com.ssafy.funding.domain.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

    public interface OrderResponseInterface {
        Integer getCount();
    }

    Optional<Order> findByUuid(String orderUuid);

    boolean existsByFundingIdAndUserId(int fundingId, int userId);

    List<Order> findByFundingId(int fundingId);

    OrderResponseInterface findByUserIdAndFundingId(Integer userId, Integer fundingInd);

    Optional<Order> findByUserIdAndFundingIdAndStatus(
            Integer userId, Integer fundingId, Boolean status);

    Slice<Order> findByUserId(int userId);
}
