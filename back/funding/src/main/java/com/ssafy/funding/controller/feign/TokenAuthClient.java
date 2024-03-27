package com.ssafy.funding.controller.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USER")
public interface TokenAuthClient {

    @GetMapping("/users/auth/test")
    String thisisTest(@RequestParam("param") String param);
}
