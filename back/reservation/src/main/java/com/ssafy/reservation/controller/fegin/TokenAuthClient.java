package com.ssafy.reservation.controller.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USER")
public interface TokenAuthClient {

    @GetMapping("/users/auth/id")
    int getUserId(@RequestHeader("Authorization") String accessToken);
}
