package com.ssafy.funding.repository;

import com.ssafy.funding.domain.Funding;
import com.ssafy.funding.domain.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    List<Movie> findByTitleContainingOrActorsContaining(String title, String actors);
}
