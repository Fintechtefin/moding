package com.ssafy.reservation.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "FUNDING")
public interface ReservationClient {

    @GetMapping("/fundings/orders/check/{fundingId}/{userId}")
    boolean checkPaymentUser(@PathVariable Integer fundingId, @PathVariable Integer userId);
}
