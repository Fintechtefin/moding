package com.ssafy.funding.controller;

import com.ssafy.funding.controller.feign.TokenAuthClient;
import com.ssafy.funding.dto.request.MovieFundingRequest;
import com.ssafy.funding.repository.FundingRepository;
import com.ssafy.funding.service.FundingService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FundingController {

    private final FundingService fundingService;

    private final TokenAuthClient tokenAuthClient;

    @Operation(summary = "무딩중 리스트 TOP10을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<FundingRepository.FundingListResponseInterface>> searchMovie(
            @RequestParam String status) {
        return ResponseEntity.ok(fundingService.getFundingList(status));
    }

    @Operation(summary = "펀딩 참여 추진을 등록합니다.")
    @PostMapping("/{movieId}/attendance")
    public ResponseEntity registerAttendance(
            @RequestHeader("accessToken") String accessToken,
            @PathVariable int movieId,
            @RequestBody MovieFundingRequest movieFundingRequest) {
        fundingService.registerAttendance(accessToken, movieId, movieFundingRequest);
        return ResponseEntity.ok("success");
    }
}
