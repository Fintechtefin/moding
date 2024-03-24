package com.ssafy.funding.repository;

import com.ssafy.funding.domain.document.MovieDocument;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieSearchCriteriaQueryRepository {

    private final ElasticsearchOperations operations;

    public List<MovieDocument> findByCriteriaCondition(String word) {
        CriteriaQuery query = createConditionCriteriaQuery(word);

        SearchHits<MovieDocument> search = operations.search(query, MovieDocument.class);
        return search.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    private CriteriaQuery createConditionCriteriaQuery(String word) {
        CriteriaQuery query = new CriteriaQuery(new Criteria());

        query.addCriteria(Criteria.where("title").contains(word));
        query.addCriteria(Criteria.where("actors").contains(word));

        return query;
    }
}
