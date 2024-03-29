package com.ssafy.funding.service;

import com.ssafy.funding.controller.feign.TokenAuthClient;
import com.ssafy.funding.domain.Movie;
import com.ssafy.funding.domain.MovieFunding;
import com.ssafy.funding.dto.request.MovieFundingRequest;
import com.ssafy.funding.repository.FundingRepository;
import com.ssafy.funding.repository.MovieFundingRepository;
import com.ssafy.funding.repository.MovieRepository;
import com.ssafy.funding.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FundingService {

    private final FundingRepository fundingRepository;
    private final TokenAuthClient tokenAuthClient;
    private final MovieFundingRepository movieFundingRepository;
    private final MovieRepository movieRepository;
    private final OrderRepository orderRepository;

    public List<FundingRepository.FundingListResponseInterface> getFundingList(String status) {
        List<FundingRepository.FundingListResponseInterface> fundingListResponseInterfaceList =
                new ArrayList<>();

        switch (status) {
            case "progress":
                fundingListResponseInterfaceList = fundingRepository.getProgressRanking();
                break;
            case "request":
                fundingListResponseInterfaceList = fundingRepository.getRequestRanking();
                break;
        }

        return fundingListResponseInterfaceList;
    }

    public void registerAttendance(
            String accessToken, int movieId, MovieFundingRequest movieFundingRequest) {
        // 헤더에 accessToken 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set("accessToken", accessToken);

        int userId = tokenAuthClient.getUserId(accessToken);

        // 참여
        Movie movie = movieRepository.findById(movieId).get();
        MovieFunding movieFunding = MovieFunding.of(movieFundingRequest, userId, movie);
        movieFundingRepository.save(movieFunding);
    }

    public FundingRepository.OpenFundingResponseInterface getOpenFundingInfo(int movieId) {

        // 있는지 확인
        Optional<Movie> movie = movieRepository.findById(movieId);

        if (!movie.get().getStatus().getValue().equals("OPEN")) {
            return null;
        }

        return fundingRepository.getOpenFundingInfo(movieId);
    }

    public boolean getFundingParticipation(String accessToken, int fundingId) {
        int userId = tokenAuthClient.getUserId(accessToken);
        //        System.out.println(userId);
        return orderRepository.existsByFundingIdAndUserId(fundingId, userId);
    }
}
