package com.ssafy.toss.controller;

import com.ssafy.toss.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;

    @GetMapping("/write")
    public ResponseEntity writeData(
            @RequestParam("key") String key, @RequestParam("value") String value) {
        log.info("write key {} and value {}", key, value);
        redisService.writeDataIntoRedis(key, value);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity getData(@RequestParam("key") String key) {
        log.info("get key {}", key);
        return ResponseEntity.ok(redisService.getDataIntoRedis(key));
    }
}
