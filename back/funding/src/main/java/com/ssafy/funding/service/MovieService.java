package com.ssafy.funding.service;

import com.ssafy.funding.domain.Movie;
import com.ssafy.funding.domain.document.MovieDocument;
import com.ssafy.funding.dto.DetailMovie;
import com.ssafy.funding.repository.MovieSearchNativeQueryRepository;
import com.ssafy.funding.repository.MovieSearchRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MovieService {

    private final MovieSearchNativeQueryRepository movieSearchNativeQueryRepository;
    private final MovieSearchRepository movieSearchRepository;

    public List<Movie> searchMovie(String word) {
        return transInfoList(movieSearchNativeQueryRepository.findByNativeCondition(word));
    }

    public Movie detailMovieBySearch(int movieId) throws IOException {
        // 로그 전송
        log.info(String.valueOf(movieId));
        // todo: 검색한 영화 db에서 찾아서 Funding 객체로 반환하기
        return null;
    }

    public List<DetailMovie> popularMovies() throws IOException {

        // todo: redis에 있는지 확인

        // 인기 검색 영화 집계
        Terms duplicateMessages = movieSearchNativeQueryRepository.getRecentTop10Movies();
        List<DetailMovie> movieList = new ArrayList<>();

        // 인기 영화 조회
        if (duplicateMessages != null) {
            for (Terms.Bucket bucket : duplicateMessages.getBuckets()) {
                String key = bucket.getKeyAsString(); // movie id
                long docCount = bucket.getDocCount(); // count 횟수
                System.out.println(key + "," + docCount + "회");

                Optional<MovieDocument> movieDocument = movieSearchRepository.findById(key);
                movieList.add(transInfoReverse(movieDocument.get()));
            }
        }

        // todo: redis에 캐싱하기

        return movieList;
    }

    private List<Movie> transInfoList(List<MovieDocument> movies) {
        return movies.stream().map(Movie::of).collect(Collectors.toList());
    }

    private Movie transInfo(MovieDocument movieDocument) {
        return Movie.of(movieDocument);
    }

    private DetailMovie transInfoReverse(MovieDocument movieDocument) {
        return DetailMovie.of(movieDocument);
    }
    ;
}
