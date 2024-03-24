package com.ssafy.funding.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories
public class ElasticSearchConfig extends AbstractElasticsearchConfig {
    @Override
    public RestHighLevelClient elasticsearchClient() {
        // http port 와 통신할 주소 -> 추후 서버 주소로 변경하기
        ClientConfiguration configuration =
                ClientConfiguration.builder().connectedTo("localhost:9200").build();
        return RestClients.create(configuration).rest();
    }
}
