package com.ssafy.funding.service;

import com.ssafy.funding.domain.Movie;
import com.ssafy.funding.domain.document.MovieDocument;
import com.ssafy.funding.repository.MovieSearchCriteriaQueryRepository;
import com.ssafy.funding.repository.MovieSearchNativeQueryRepository;
import java.util.List;
import java.util.stream.Collectors;

import com.ssafy.funding.repository.MovieSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {

    private final MovieSearchCriteriaQueryRepository movieSearchCriteriaQueryRepository;
    private final MovieSearchNativeQueryRepository movieSearchNativeQueryRepository;
//    private final MovieSearchRepository movieSearchRepository;

    public List<Movie> searchMovie(String word) {
//        return transInfo(movieSearchCriteriaQueryRepository.findByCriteriaCondition(word));
        return transInfo(movieSearchNativeQueryRepository.findByNativeCondition(word));
    }

//    public List<Movie> searchMovies(String keyword) {
//        // title 또는 actors 필드에서 검색
//        return transInfo(movieSearchRepository.findByTitleContainingOrActorsContaining(keyword));
//    }

    private List<Movie> transInfo(List<MovieDocument> movies) {
        System.out.println("반환!!!!");
        System.out.println(movies.size());
        return movies.stream().map(Movie::of).collect(Collectors.toList());
    }
}
