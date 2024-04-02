package com.ssafy.toss.config;

import io.lettuce.core.ReadFrom;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {
    private final RedisInfo info;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        LettuceClientConfiguration clientConfig =
                LettuceClientConfiguration.builder()
                        .readFrom(
                                ReadFrom.REPLICA_PREFERRED) // replica에서 우선적으로 읽지만 replica에서 읽어오지 못할
                        // 경우 Master에서 읽어옴
                        .build();
        // replica 설정
        RedisStaticMasterReplicaConfiguration slaveConfig =
                new RedisStaticMasterReplicaConfiguration(
                        info.getMaster().getHost(), info.getMaster().getPort());
        // 설정에 slave 설정 값 추가
        info.getSlaves().forEach(slave -> slaveConfig.addNode(slave.getHost(), slave.getPort()));
        return new LettuceConnectionFactory(slaveConfig, clientConfig);
    }
}
