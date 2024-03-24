package com.ssafy.funding.repository;

import com.ssafy.funding.domain.document.MovieDocument;
import java.util.List;

// extends ElasticsearchRepository<MovieDocument,Long>
public interface MovieSearchRepository {
    List<MovieDocument> findByTitleContainingOrActorsContaining(String keyword);
}
