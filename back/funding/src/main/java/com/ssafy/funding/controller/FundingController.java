package com.ssafy.funding.controller;

import com.ssafy.funding.repository.FundingRepository;
import com.ssafy.funding.service.FundingService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FundingController {

    private final FundingService fundingService;

    @Operation(summary = "무딩중 리스트 TOP10을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<FundingRepository.FundingListResponseInterface>> searchMovie(
            @RequestParam String status) {
        return ResponseEntity.ok(fundingService.getFundingList(status));
    }
}
