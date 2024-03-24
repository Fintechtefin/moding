package com.ssafy.funding.repository;

import com.ssafy.funding.domain.document.MovieDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

// extends ElasticsearchRepository<MovieDocument,Long>
public interface MovieSearchRepository{
    List<MovieDocument> findByTitleContainingOrActorsContaining(String keyword);
}
