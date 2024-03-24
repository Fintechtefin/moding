package com.ssafy.funding.repository;

import com.ssafy.funding.domain.document.MovieDocument;
import java.util.Optional;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MovieSearchRepository extends ElasticsearchRepository<MovieDocument, String> {
    Optional<MovieDocument> findById(String movieId);
}
