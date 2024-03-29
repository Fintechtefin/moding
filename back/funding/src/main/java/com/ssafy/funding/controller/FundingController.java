package com.ssafy.funding.controller;

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

    @Operation(summary = "현재 열린 펀딩이 있다면 정보를 반환합니다.")
    @GetMapping("/open/{movieId}")
    public ResponseEntity<FundingRepository.OpenFundingResponseInterface> getOpenFundingInfo(
            @PathVariable int movieId) {
        return ResponseEntity.ok(fundingService.getOpenFundingInfo(movieId));
    }

    @Operation(summary = "현재 로그인한 유저의 펀딩 참여 여부를 반환합니다.")
    @GetMapping("/{fundingId}/participation")
    public ResponseEntity getFundingParticipation(
            @RequestHeader("accessToken") String accessToken,
            @PathVariable("fundingId") int fundingId) {
        return ResponseEntity.ok(fundingService.getFundingParticipation(accessToken, fundingId));
    }

    @Operation(summary = "예매한 티켓에 대한 펀딩과 영화 정보를 조회합니다.")
    @GetMapping("/get/{fundingId}")
    public ResponseEntity<?> getTicketInfo(
            @RequestHeader("accessToken") String accessToken,
            @PathVariable("fundingId") int fundingId) {
        return ResponseEntity.ok(fundingService.getTicketInfo(accessToken, fundingId));
    }

    @Operation(summary = "현재 로그인한 유저가 참여한 펀딩 목록을 반환합니다.")
    @GetMapping("/participation")
    public ResponseEntity getFundingParticipation(
            @RequestHeader("accessToken") String accessToken) {
        return ResponseEntity.ok(fundingService.getMyFundings(accessToken));
    }
}
