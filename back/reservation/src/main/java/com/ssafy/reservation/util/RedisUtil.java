package com.ssafy.reservation.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisUtil {

    private final RedisTemplate redisTemplate;

    public String getData(String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void setData(String key, String value) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    public Integer getValue(String key) {
        ValueOperations<String, Integer> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void setValue(String key, Integer value) {
        ValueOperations<String, Integer> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    public void deleteData(String key) {
        redisTemplate.delete(key);
    }
}
