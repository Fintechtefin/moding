package com.ssafy.funding.service;

import com.ssafy.funding.domain.Movie;
import com.ssafy.funding.domain.document.MovieDocument;
import com.ssafy.funding.dto.DetailMovie;
import com.ssafy.funding.repository.MovieRepository;
import com.ssafy.funding.repository.MovieSearchNativeQueryRepository;
import com.ssafy.funding.util.RedisUtil;
import java.io.IOException;
import java.util.*;
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
    private final MovieRepository movieRepository;
    private final RedisUtil redisUtil;

    public List<DetailMovie> searchMovie(String word) {
        List<DetailMovie> movies = movieRepository.findByTitleContainingOrActorsContaining(word, word);
        return movies;
        //        return
        // transInfoList(movieSearchNativeQueryRepository.findByNativeCondition(word));
    }

    public Optional<Movie> detailMovieBySearch(int movieId) throws IOException {
        // 로그 전송
        log.info(String.valueOf(movieId));
        // todo: Funding 객체로 반환하기
        return movieRepository.findById(movieId);
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

                //                Optional<MovieDocument> movieDocument =
                // movieSearchRepository.findById(key);
                Optional<Movie> movie = movieRepository.findById(Integer.parseInt(key));
                //                movieList.add(transInfoReverse(movieDocument.get()));
                movieList.add(transInfoDetail(movie.get()));
            }
        }

        // todo: redis에 캐싱하기

        return movieList;
    }

    public List<DetailMovie> getMovieList(String genre, String sort, int page) {

        List<DetailMovie> movieList = new ArrayList<>();
        final String key = "movie_list_" + genre + "_" + sort + "_" + page;

        if (redisUtil.getObject(key) != null) {
            movieList = (List<DetailMovie>) redisUtil.getObject(key);
            return movieList.subList((page - 1) * 20, page * 20);
        }

        movieList = movieRepository.findMoviesByParentGenreId(genre);
        Comparator<DetailMovie> comparator;

        if (sort.equals("titleDesc") || sort.equals("titleAsc")) {
            if (sort.equals("titleDesc")) {
                comparator = Comparator.comparing(DetailMovie::getTitle).reversed();
            } else {
                comparator = Comparator.comparing(DetailMovie::getTitle);
            }
            Collections.sort(movieList, comparator);
            redisUtil.setObject(key, movieList);
        } else {
            if (sort.equals("likeDesc")) {
                comparator = Comparator.comparingLong(DetailMovie::getLikeCnt).reversed();
            } else {
                comparator = Comparator.comparingLong(DetailMovie::getLikeCnt);
            }
            Collections.sort(movieList, comparator);
        }

        return movieList.subList((page - 1) * 20, page * 20);
    }

    private List<Movie> transInfoList(List<MovieDocument> movies) {
        return movies.stream().map(Movie::of).collect(Collectors.toList());
    }

    private Movie transInfo(MovieDocument movieDocument) {
        return Movie.of(movieDocument);
    }

    private DetailMovie transInfoDetail(Movie movie) {
        return DetailMovie.of(movie);
    }
}
