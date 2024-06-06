package com.ssafy.funding.repository;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

import com.ssafy.funding.config.ElasticSearchConfig;
import com.ssafy.funding.domain.document.MovieDocument;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MovieSearchNativeQueryRepository {

    private final ElasticsearchOperations operations;
    private final ElasticSearchConfig elasticSearchConfig;

    public List<MovieDocument> findByNativeCondition(String word) {
        PageRequest pageable = PageRequest.of(0, 3000);
        NativeSearchQuery searchQuery =
                new NativeSearchQueryBuilder()
                        .withQuery(QueryBuilders.multiMatchQuery(word, "title", "actors"))
                        .build();
        searchQuery.setPageable(pageable);
        SearchHits<MovieDocument> searchHits = operations.search(searchQuery, MovieDocument.class);
        return searchHits.stream().map(hit -> hit.getContent()).collect(Collectors.toList());
    }

    private NativeSearchQuery createConditionNativeQuery(String word) {

        NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();

        // BoolQueryBuilder mustBoolQueryBuilder = boolQuery();
        BoolQueryBuilder shouldBoolQueryBuilder = boolQuery();

        shouldBoolQueryBuilder
                .should(QueryBuilders.wildcardQuery("title", "*" + word + "*"))
                .should(QueryBuilders.wildcardQuery("actors", "*" + word + "*"));

        query.withQuery(shouldBoolQueryBuilder);

        return query.build();
    }

    public Terms getRecentTop10Movies() throws IOException {

        RestHighLevelClient restHighLevelClient = elasticSearchConfig.elasticsearchClient();

        // 최근 1시간
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime truncatedMinus = now.minusHours(1).truncatedTo(ChronoUnit.HOURS);
        LocalDateTime truncatedNow = now.truncatedTo(ChronoUnit.HOURS);
        long startMillis = truncatedMinus.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endMillis = truncatedNow.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        // test 용
        //        LocalDateTime now = LocalDateTime.now();
        //        LocalDateTime truncatedNow = now.truncatedTo(ChronoUnit.HOURS);
        //        long startMillis =
        // truncatedNow.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        //        long endMillis = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilders.rangeQuery("@timestamp").gte(startMillis).lte(endMillis));

        sourceBuilder.aggregation(
                AggregationBuilders.terms("duplicate_messages")
                        .field("special_flag_id.keyword")
                        .size(10));

        SearchRequest searchRequest = new SearchRequest("accesslogs*");
        searchRequest.source(sourceBuilder);

        // 검색 결과
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        // 검색 결과에서 집계결과만 빼옴
        Terms duplicateMessages = response.getAggregations().get("duplicate_messages");

        return duplicateMessages;
    }
}
