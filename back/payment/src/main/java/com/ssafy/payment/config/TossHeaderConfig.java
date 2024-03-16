package com.ssafy.payment.config;

import com.ssafy.payment.properties.TossPaymentsProperties;
import feign.auth.BasicAuthRequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class TossHeaderConfig {
    private final TossPaymentsProperties tossPaymentsProperties;

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(tossPaymentsProperties.getSecretKey(), "");
    }
}
