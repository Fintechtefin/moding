package com.ssafy.funding.controller;

import com.ssafy.funding.domain.Movie;
import com.ssafy.funding.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @Operation(summary = "영화를 검색합니다.")
    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovie(@RequestParam String word) {
        return ResponseEntity.ok(movieService.searchMovie(word));
    }
}
