package com.ssafy.payment.config;

import com.ssafy.payment.properties.TossPaymentsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({TossPaymentsProperties.class})
@Configuration
public class ConfigurationPropertiesConfig {}
