package com.ssafy.funding.controller;

import com.ssafy.funding.dto.response.MovieDescResponse;
import com.ssafy.funding.dto.response.MovieDetailResponse;
import com.ssafy.funding.dto.response.MovieRankingResponse;
import com.ssafy.funding.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
// @CrossOrigin(origins = "*")
public class MovieController {

    private final MovieService movieService;

    @Operation(summary = "영화를 검색합니다.")
    @GetMapping("/search")
    public ResponseEntity<List<MovieDetailResponse>> searchMovie(@RequestParam String word) {
        return ResponseEntity.ok(movieService.searchMovie(word));
    }

    @Operation(summary = "검색을 통해 영화(펀딩) 상세 정보를 조회합니다.")
    @GetMapping("/search/{movieId}")
    public ResponseEntity<MovieDescResponse> detailMovieBySearch(
            @RequestHeader(value = "Authorization", required = false) String accessToken, @PathVariable int movieId)
            throws IOException {
        return ResponseEntity.ok(movieService.detailMovieBySearch(movieId, accessToken));
    }

    @Operation(summary = "영화(펀딩) 상세 정보를 조회합니다.")
    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDescResponse> detailMovie(
            @RequestHeader(value = "Authorization", required = false) String accessToken, @PathVariable int movieId)
            throws IOException {
        return ResponseEntity.ok(movieService.detailMovie(movieId, accessToken));
    }

    @Operation(summary = "실시간 인기 영화를 조회합니다.")
    @GetMapping("/popular")
    public ResponseEntity<List<MovieRankingResponse>> popularMovies(@RequestParam int time)
            throws IOException {
        return ResponseEntity.ok(movieService.popularMovies(time));
    }

    @Operation(summary = "장르별 영화를 조회합니다.")
    @GetMapping
    public ResponseEntity<?> listMovieByGenre(
            @RequestParam int genre, @RequestParam String sort, @RequestParam int page) {
        return ResponseEntity.ok(movieService.getMovieList(genre, sort, page));
    }

    @Operation(summary = "영화 좋아요를 등록합니다.")
    @GetMapping("/{movieId}/likes")
    public ResponseEntity<?> likeMovie(
            @RequestHeader("Authorization") String accessToken, @PathVariable int movieId) {
        return ResponseEntity.ok(movieService.likeMovie(accessToken, movieId));
    }

    @Operation(summary = "영화 좋아요를 취소합니다.")
    @DeleteMapping("/{movieId}/likes")
    public ResponseEntity<?> cancelLikeMovie(
            @RequestHeader("Authorization") String accessToken, @PathVariable int movieId) {
        return ResponseEntity.ok(movieService.cancelLikeMovie(accessToken, movieId));
    }

    @Operation(description = "현재 로그인한 유저가 좋아요한 영화 목록을 반환합니다. ")
    @GetMapping("/like")
    public ResponseEntity<?> getMyLikeList(@RequestHeader("Authorization") String accessToken) {
        return ResponseEntity.ok().body(movieService.getMyLikeList(accessToken));
    }

    @Operation(description = "현재 로그인한 유저가 펀딩 요청한 영화 목록을 반환합니다.")
    @GetMapping("/request")
    public ResponseEntity<?> getMyRequestList(@RequestHeader("Authorization") String accessToken) {
        return ResponseEntity.ok().body(movieService.getMyRequestList(accessToken));
    }
}
