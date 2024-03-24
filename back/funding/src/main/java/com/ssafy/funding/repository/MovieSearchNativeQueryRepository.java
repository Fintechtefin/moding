package com.ssafy.funding.repository;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

import com.ssafy.funding.domain.document.MovieDocument;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieSearchNativeQueryRepository {

    private final ElasticsearchOperations operations;

    public List<MovieDocument> findByNativeCondition(String word) {
        PageRequest pageable = PageRequest.of(0, 3000);
        NativeSearchQuery searchQuery =
                new NativeSearchQueryBuilder()
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

        shouldBoolQueryBuilder
                .should(QueryBuilders.wildcardQuery("title", "*" + word + "*"))
                .should(QueryBuilders.wildcardQuery("actors", "*" + word + "*"));

        query.withQuery(shouldBoolQueryBuilder);

        return query.build();
    }
}
