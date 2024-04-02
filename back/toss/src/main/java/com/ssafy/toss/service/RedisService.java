package com.ssafy.toss.service;

import com.ssafy.toss.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisUtil redisUtil;

    public void writeDataIntoRedis(String key, String value) {
        redisUtil.setData(key, value);
    }

    public String getDataIntoRedis(String key) {
        return redisUtil.getData(key);
    }
}
