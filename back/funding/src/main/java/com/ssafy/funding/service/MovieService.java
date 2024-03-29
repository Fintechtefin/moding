package com.ssafy.funding.service;

import com.ssafy.funding.controller.feign.TokenAuthClient;
import com.ssafy.funding.domain.Movie;
import com.ssafy.funding.domain.MovieLike;
import com.ssafy.funding.domain.document.MovieDocument;
import com.ssafy.funding.dto.response.*;
import com.ssafy.funding.repository.MovieLikeRepository;
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
    private final MovieLikeRepository movielikeRepository;
    private final TokenAuthClient tokenAuthClient;

    public List<MovieDetailResponse> searchMovie(String word) {
        List<MovieDetailResponse> movies =
                movieRepository.findByTitleContainingOrActorsContaining(word, word);
        System.out.println(movies.size());
        return movies;
    }

    public MovieDescResponse detailMovieBySearch(int movieId) throws IOException {
        // 로그 전송
        log.info(String.valueOf(movieId));
        MovieDescResponse movieDescResponse = detailMovie(movieId);
        return movieDescResponse;
    }

    public MovieDescResponse detailMovie(int movieId) {

        // 영화 정보 가져오기
        Optional<MovieSummaryResponse> movieSummaryResponse =
                movieRepository.getMovieDetailById(movieId);

        MovieDescResponse movieDescResponse = MovieDescResponse.of(movieSummaryResponse.get());

        // genre 가져오기
        Optional<List<String>> genreList = movieRepository.getGenreById(movieId);
        movieDescResponse = MovieDescResponse.setGenre(movieDescResponse, genreList.get());

        // total 지정
        String key = "total_cnt_" + movieId;

        // test
        redisUtil.setData(key, "10");

        int accumulate = Integer.parseInt(redisUtil.getData(key));
        movieDescResponse = MovieDescResponse.setTotal(movieDescResponse, accumulate);

        int success = movieRepository.getSuccessCountById(movieId);

        return MovieDescResponse.setSuccess(movieDescResponse, success);
    }

    public List<MovieRankingResponse> popularMovies(int time) throws IOException {

        String redisKey = "movie_ranking_" + (time);

        if (redisUtil.getObject(redisKey) != null) {
            System.out.println("Redis Hit!!!");
            return (List<MovieRankingResponse>) redisUtil.getObject(redisKey);
        }

        // 인기 검색 영화 집계
        Terms duplicateMessages = movieSearchNativeQueryRepository.getRecentTop10Movies();
        List<MovieRankingResponse> movieList = new ArrayList<>();

        // 인기 영화 조회
        if (duplicateMessages != null) {
            for (Terms.Bucket bucket : duplicateMessages.getBuckets()) {
                String key = bucket.getKeyAsString(); // movie id
                long docCount = bucket.getDocCount(); // count 횟수

                Optional<Movie> movie = movieRepository.findById(Integer.parseInt(key));
                MovieRankingResponse movieRankingResponse = transInfoRanking(movie.get());
                movieRankingResponse.setSearchCnt(docCount);
                movieRankingResponse.setRefreshTime(time - 1);

                movieList.add(movieRankingResponse);
            }
        }

        redisUtil.setObject(redisKey, movieList);
        redisKey = "movie_ranking_" + (time - 1);
        if (redisUtil.getObject(redisKey) != null) {
            redisUtil.deleteData(redisKey);
        }

        return movieList;
    }

    public List<MovieDetailResponse> getMovieList(int genre, String sort, int page) {
        // todo: 리팩토링 필수
        List<MovieDetailResponse> movieList = new ArrayList<>();
        final String key = "movie_list_" + genre + "_" + sort + "_" + page;

        if (redisUtil.getObject(key) != null) {
            movieList = (List<MovieDetailResponse>) redisUtil.getObject(key);
            //            System.out.println("Redis Hit!!!");
            if ((page - 1) * 21 > movieList.size()) return null;
            if (page * 21 >= movieList.size() && (page - 1) * 21 < movieList.size()) {
                return movieList.subList((page - 1) * 21, movieList.size());
            }
            return movieList.subList((page - 1) * 21, page * 21);
        }

        movieList = movieRepository.findMoviesByParentGenreId(genre);
        System.out.println(movieList.size());
        Comparator<MovieDetailResponse> comparator;

        if (sort.equals("titleDesc") || sort.equals("titleAsc")) {
            if (sort.equals("titleDesc")) {
                comparator = Comparator.comparing(MovieDetailResponse::getTitle).reversed();
            } else {
                comparator = Comparator.comparing(MovieDetailResponse::getTitle);
            }
            Collections.sort(movieList, comparator);
            redisUtil.setObject(key, movieList);
        } else {
            if (sort.equals("likeDesc")) {
                comparator = Comparator.comparingLong(MovieDetailResponse::getLikeCnt).reversed();
            } else {
                comparator = Comparator.comparingLong(MovieDetailResponse::getLikeCnt);
            }
            Collections.sort(movieList, comparator);
        }

        if ((page - 1) * 21 > movieList.size()) return null;
        if (page * 21 >= movieList.size() && (page - 1) * 21 < movieList.size()) {
            return movieList.subList((page - 1) * 21, movieList.size());
        }
        return movieList.subList((page - 1) * 21, page * 21);
    }

    private List<Movie> transInfoList(List<MovieDocument> movies) {
        return movies.stream().map(Movie::of).collect(Collectors.toList());
    }

    private Movie transInfo(MovieDocument movieDocument) {
        return Movie.of(movieDocument);
    }

    private MovieDetailResponse transInfoDetail(Movie movie) {
        return MovieDetailResponse.of(movie);
    }

    private MovieRankingResponse transInfoRanking(Movie movie) {
        return MovieRankingResponse.of(movie);
    }

    public MovieLikeResponse likeMovie(String accessToken, int movieId) {
        int userId = tokenAuthClient.getUserId(accessToken);

        movielikeRepository.save(MovieLike.of(userId, movieId));

        String key = "likes_" + movieId;
        if (redisUtil.getData(key) == null) {
            long likeCnt = movielikeRepository.countByMovieId(movieId);
            redisUtil.setData(key, String.valueOf(likeCnt));
        } else {
            int count = Integer.parseInt(redisUtil.getData(key));
            redisUtil.deleteData(key);
            redisUtil.setData(key, String.valueOf(count + 1));
        }

        return MovieLikeResponse.of(true);
    }

    public MovieLikeResponse cancelLikeMovie(String accessToken, int movieId) {
        int userId = tokenAuthClient.getUserId(accessToken);

        MovieLike movieLike = movielikeRepository.findByMovieIdAndUserId(movieId, userId);
        if (movieLike == null) {
            return MovieLikeResponse.of(false);
        }
        movielikeRepository.delete(movieLike);

        String key = "likes_" + movieId;

        int count = Integer.parseInt(redisUtil.getData(key));
        redisUtil.deleteData(key);
        redisUtil.setData(key, String.valueOf(count - 1));

        return MovieLikeResponse.of(true);
    }
}
