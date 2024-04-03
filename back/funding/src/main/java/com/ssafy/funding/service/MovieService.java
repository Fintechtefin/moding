package com.ssafy.funding.service;

import com.ssafy.funding.controller.feign.TokenAuthClient;
import com.ssafy.funding.domain.Movie;
import com.ssafy.funding.domain.MovieFunding;
import com.ssafy.funding.domain.MovieLike;
import com.ssafy.funding.domain.document.MovieDocument;
import com.ssafy.funding.dto.response.*;
import com.ssafy.funding.repository.*;
import com.ssafy.funding.util.RedisUtil;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.data.domain.Slice;
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
    private final MovieLikeRepository movieLikeRepository;
    private final TokenAuthClient tokenAuthClient;
    private final MovieFundingRepository movieFundingRepository;
    private final MovieQueryRepository movieQueryRepository;

    public List<MovieDetailResponse> searchMovie(String word) {
        List<MovieDetailResponse> movies =
                movieRepository.findByTitleContainingOrActorsContaining(word, word);
        //        System.out.println(movies.size());
        return movies;
    }

    public MovieDescResponse detailMovieBySearch(int movieId, String accessToken)
            throws IOException {
        // 로그 전송
        log.info(String.valueOf(movieId));
        MovieDescResponse movieDescResponse = detailMovie(movieId, accessToken);
        return movieDescResponse;
    }

    public MovieDescResponse detailMovie(int movieId, String accessToken) {

        int userId = 0;
        MovieSummaryResponse movieSummaryResponse;
        MovieDescResponse movieDescResponse;

        if (accessToken != null) {
            userId = tokenAuthClient.getUserId(accessToken);
        }

        String redisKey = "movie_" + movieId; // 상세 정보 저장용

        if (redisUtil.getObject(redisKey) != null) {
            movieDescResponse = (MovieDescResponse) redisUtil.getObject(redisKey);
            //            System.out.println("Redis Detail Hit!!!");
        } else {
            movieSummaryResponse = movieRepository.getMovieDetailById(movieId).get();

            movieDescResponse = MovieDescResponse.of(movieSummaryResponse);
            Optional<List<String>> genreList = movieRepository.getGenreById(movieId);
            movieDescResponse = MovieDescResponse.setGenre(movieDescResponse, genreList.get());
            redisUtil.setObject(redisKey, movieDescResponse);
        }

        // total 지정 (누적 요청 수)
        redisKey = "total_cnt_" + movieId;

        // 임시 값
        redisUtil.setData(redisKey, "100");

        int accumulate = Integer.parseInt(redisUtil.getData(redisKey));
        movieDescResponse = MovieDescResponse.setTotal(movieDescResponse, accumulate);

        movieDescResponse =
                MovieDescResponse.setSuccess(
                        movieDescResponse, movieRepository.getSuccessCountById(movieId));
        if (accessToken != null) {
            movieDescResponse =
                    MovieDescResponse.setLike(
                            movieDescResponse,
                            movieLikeRepository.existsByUserIdAndMovieId(userId, movieId));
            movieDescResponse =
                    MovieDescResponse.setRequest(
                            movieDescResponse,
                            movieFundingRepository.existsByUserIdAndMovieId(userId, movieId));
        }

        return movieDescResponse;
    }

    public List<MovieRankingResponse> popularMovies() throws IOException {
        int time = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).getHour(); // 현재 시각

        String redisKey = "movie_ranking_" + (time - 1); // 직전 기록 가져오기

        if (redisUtil.getObject(redisKey) != null) {
            //            System.out.println("Redis Hit!!!");
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
        redisKey = "movie_ranking_" + (time - 2);
        if (redisUtil.getObject(redisKey) != null) {
            redisUtil.deleteData(redisKey);
        }

        return movieList;
    }

    public Map<String, Object> getMovieList(int genreId, String sort, int page) {

        List<MovieRepository.MovieGenreListResponse> movieList = new ArrayList<>();

        if (sort.equals("likeAsc"))
            movieList = movieRepository.getMovieByGenreLikeAsc(genreId, (page - 1) * 21);
        else if (sort.equals("likeDesc"))
            movieList = movieRepository.getMovieByGenreLikeDesc(genreId, (page - 1) * 21);
        else if (sort.equals("titleDesc"))
            movieList = movieRepository.getMovieByGenreTitleDesc(genreId, (page - 1) * 21);
        else movieList = movieRepository.getMovieByGenreTitleAsc(genreId, (page - 1) * 21);

        String key = "genreCount_" + genreId;
        if (redisUtil.getData(key) == null) {
            redisUtil.setData(key, String.valueOf(movieRepository.getMovieCountByGenreId(genreId)));
        }

        Map<String, Object> result = new HashMap<>();
        result.put("movieList", movieList);
        result.put("totalCnt", redisUtil.getData(key));

        return result;
    }

    //        public List<MovieGenreResponse> getMovieList(int genreId, String sort, int page) {
    //            PageRequest pageRequest=PageRequest.of(page-1,21);  // page << 1부터 받으니까
    //
    //            final Slice<MovieGenreResponse> slice =
    //                    movieQueryRepository.findByGenre(
    //                            GenreSearchCondition.builder()
    //                                    .parentGenreId(genreId)
    //                                    .order(sort)
    //                                    .build(),
    //                            pageRequest);
    //
    //            // Slice에서 Content를 가져와 List로 변환
    //            List<MovieGenreResponse> movieList = slice.getContent();
    //
    //            // totalCnt add
    //
    //            return movieList;
    //
    //        }

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

        movieLikeRepository.save(MovieLike.of(userId, movieId));

        String key = "likes_" + movieId;
        if (redisUtil.getData(key) == null) {
            long likeCnt = movieLikeRepository.countByMovieId(movieId);
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

        MovieLike movieLike = movieLikeRepository.findByMovieIdAndUserId(movieId, userId);

        if (movieLike == null) {
            return MovieLikeResponse.of(false);
        }
        movieLikeRepository.delete(movieLike);

        String key = "likes_" + movieId;

        int count = Integer.parseInt(redisUtil.getData(key));
        redisUtil.deleteData(key);
        redisUtil.setData(key, String.valueOf(count - 1));

        return MovieLikeResponse.of(true);
    }

    public List<MovieLikeRepository.UserLikeMovieResponse> getMyLikeList(String accessToken) {
        int userId = tokenAuthClient.getUserId(accessToken);
        return movieLikeRepository.getMyLikeList(userId);
    }

    public RequestMovieListResponse getMyRequestList(String accessToken) {

        int userId = tokenAuthClient.getUserId(accessToken);

        Slice<MovieFunding> movieFundings = movieFundingRepository.findByUserId(userId);

        return RequestMovieListResponse.of(
                movieFundings.stream()
                        .map(
                                movieFunding ->
                                        RequestMovieResponse.builder()
                                                .title(movieFunding.getMovie().getTitle())
                                                .movieId(movieFunding.getMovie().getId())
                                                .poster(movieFunding.getMovie().getPoster())
                                                .requestCnt(
                                                        getRequestCount(
                                                                movieFunding.getMovie().getId()))
                                                .build())
                        .collect(Collectors.toList()));
    }

    private Integer getRequestCount(int movieId) {
        Integer requestCount = redisUtil.getIntegerData("movie_funding_" + movieId);

        if (requestCount != null) {
            return requestCount;
        }

        List<MovieFunding> movieFundingList = movieFundingRepository.findByMovieId(movieId);
        return movieFundingList.size();
    }
}
