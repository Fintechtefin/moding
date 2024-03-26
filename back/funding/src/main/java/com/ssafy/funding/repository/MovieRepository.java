package com.ssafy.funding.repository;

import com.ssafy.funding.domain.Movie;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    List<Movie> findByTitleContainingOrActorsContaining(String title, String actors);
}
