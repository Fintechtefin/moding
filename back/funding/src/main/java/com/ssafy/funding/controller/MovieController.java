package com.ssafy.funding.controller;

import com.ssafy.funding.domain.Movie;
import com.ssafy.funding.dto.DetailMovie;
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
public class MovieController {

    private final MovieService movieService;

    @Operation(summary = "영화를 검색합니다.")
    @GetMapping("/search")
    public ResponseEntity<List<DetailMovie>> searchMovie(@RequestParam String word) {
        return ResponseEntity.ok(movieService.searchMovie(word));
    }

    @Operation(summary = "검색을 통해 영화(펀딩) 상세 정보를 조회합니다.")
    @GetMapping("/search/{movieId}")
    public ResponseEntity<Movie> detailMovieBySearch(@PathVariable int movieId) throws IOException {
        return ResponseEntity.ok(movieService.detailMovieBySearch(movieId).get());
    }

    @Operation(summary = "실시간 인기 영화를 조회합니다.")
    @GetMapping("/popular")
    public ResponseEntity<List<DetailMovie>> popularMovies() throws IOException {
        return ResponseEntity.ok(movieService.popularMovies());
    }

    @Operation(summary = "장르별 영화를 조회합니다.")
    @GetMapping
    public ResponseEntity<List<DetailMovie>> listMovieByGenre(
            @RequestParam String genre, @RequestParam String sort, @RequestParam int page) {
        return ResponseEntity.ok(movieService.getMovieList(genre, sort, page));
    }


}
