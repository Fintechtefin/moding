package com.ssafy.reservation.controller;

import com.ssafy.reservation.dto.response.FundingInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "FUNDING")
public interface ReservationClient {

    @GetMapping("/fundings/orders/check/{fundingId}/{userId}")
    boolean checkPaymentUser(
            @PathVariable("fundingId") int fundingId, @PathVariable("userId") int userId);

    @GetMapping("/fundings/get/{fundingId}")
    FundingInfoResponse getTicketInfo(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable("fundingId") Integer fundingId);
}
