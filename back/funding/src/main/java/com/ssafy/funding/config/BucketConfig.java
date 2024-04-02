package com.ssafy.funding.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BucketConfig {
    @Bean
    public Bucket bucket() {

        // 1초에 20개의 토큰씩 충전
        final Refill refill = Refill.intervally(20, Duration.ofSeconds(1));

        // 버킷의 크기는 20개
        final Bandwidth limit = Bandwidth.classic(20, refill);

        return Bucket.builder().addLimit(limit).build();
    }
}
