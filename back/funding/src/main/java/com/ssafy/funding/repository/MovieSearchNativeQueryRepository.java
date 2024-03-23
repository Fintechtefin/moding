package com.ssafy.funding.repository;

import com.ssafy.funding.domain.document.MovieDocument;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

@Component
@RequiredArgsConstructor
public class MovieSearchNativeQueryRepository {

    private final ElasticsearchOperations operations;
    public List<MovieDocument> findByNativeCondition(String word) {
        PageRequest pageable = PageRequest.of(0, 3000);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(word, "title", "actors"))
                .build();
        searchQuery.setPageable(pageable);
//        Query searchQuery = createConditionNativeQuery(word);
        SearchHits<MovieDocument> searchHits = operations.search(searchQuery, MovieDocument.class);
        return searchHits.stream().map(hit -> hit.getContent()).collect(Collectors.toList());
    }

    private NativeSearchQuery createConditionNativeQuery(String word) {

        NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();

        // BoolQueryBuilder mustBoolQueryBuilder = boolQuery();
        BoolQueryBuilder shouldBoolQueryBuilder = boolQuery();
        BoolQueryBuilder filterBoolQueryBuilder = boolQuery();

        shouldBoolQueryBuilder.should(QueryBuilders.wildcardQuery("title","*"+word+"*"))
                        .should(QueryBuilders.wildcardQuery("actors","*"+word+"*"));

        query.withQuery(shouldBoolQueryBuilder);

        return query.build();
    }

}
