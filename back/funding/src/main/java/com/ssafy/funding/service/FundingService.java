package com.ssafy.funding.service;

import static com.ssafy.funding.exception.global.CustomExceptionStatus.FUNDING_NOT_FOUND;
import static com.ssafy.funding.exception.global.CustomExceptionStatus.ORDER_NOT_FOUND;

import com.ssafy.common.dto.response.FundingInfoResponse;
import com.ssafy.funding.controller.feign.TokenAuthClient;
import com.ssafy.funding.domain.*;
import com.ssafy.funding.dto.request.MovieFundingRequest;
import com.ssafy.funding.dto.response.*;
import com.ssafy.funding.exception.BadRequestException;
import com.ssafy.funding.mapper.FundingMapper;
import com.ssafy.funding.repository.*;
import com.ssafy.funding.util.RedisUtil;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
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
    private final RedisUtil redisUtil;
    private final FundingMapper fundingMapper;

    public Object getFundingList(String status) {
        Object result = null;

        switch (status) {
            case "progress":
                result = fundingRepository.getProgressRanking();
                break;
            case "request":
                result = fundingRepository.getRequestRanking();
                break;
        }
        return result;
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

    public FundingRepository.OpenFundingResponseInterface getOpenFundingInfo(
            int movieId, String accessToken) {

        int userId = 0;

        if (accessToken != null) userId = tokenAuthClient.getUserId(accessToken);

        // 있는지 확인
        Optional<Movie> movie = movieRepository.findById(movieId);

        if (movie.get().getStatus().getValue().equals("NONE")
                || movie.get().getStatus().getValue().equals("CLOSED")) {
            return null;
        }

        return fundingRepository.getOpenFundingInfo(movieId, userId);
    }

    public boolean getFundingParticipation(String accessToken, int fundingId) {
        int userId = tokenAuthClient.getUserId(accessToken);
        //        System.out.println(userId);
        return orderRepository.existsByFundingIdAndUserId(fundingId, userId);
    }

    public FundingInfoResponse getTicketInfo(String accessToken, Integer fundingId) {
        int userId = tokenAuthClient.getUserId(accessToken);
        System.out.println(userId);

        Funding funding =
                fundingRepository
                        .findById(fundingId)
                        .orElseThrow(() -> new BadRequestException(FUNDING_NOT_FOUND));

        Order order =
                orderRepository
                        .findByUserIdAndFundingIdAndStatus(userId, fundingId, true)
                        .orElseThrow(() -> new BadRequestException(ORDER_NOT_FOUND));

        return fundingMapper.fundingToFundingInfoResponse(funding, order.getCount());
    }

    public JoinFundingListResponse getMyFundings(String accessToken) {
        int userId = tokenAuthClient.getUserId(accessToken);
        Slice<Order> orders = orderRepository.findByUserId(userId);

        return JoinFundingListResponse.of(
                orders.stream()
                        .filter(o -> o.isStatus()) // 결제를 완료한 상태
                        .filter(o -> !o.getFunding().isTimeAfterEndAt())
                        .map(
                                order ->
                                        JoinFundingResponse.builder()
                                                .id(order.getId())
                                                .movieId(order.getFunding().getMovie().getId())
                                                .orderUuid(order.getUuid())
                                                .movieTitle(
                                                        order.getFunding().getMovie().getTitle())
                                                .moviePoster(
                                                        order.getFunding().getMovie().getPoster())
                                                .endAt(order.getFunding().getEndAt())
                                                .recruitedCount(order.getFunding().getPeopleCount())
                                                .participantCount(
                                                        getFundingParticipantCount(
                                                                order.getFunding().getId()))
                                                .build())
                        .collect(Collectors.toList()));
    }

    public int getFundingParticipantCount(int fundingId) {
        Integer participantCount = redisUtil.getIntegerData("funding_" + fundingId);

        if (participantCount != null) {
            return participantCount;
        }

        /* Cache에 데이터가 없다면 DB에서 조회 */
        List<Order> orderList = orderRepository.findByFundingId(fundingId);

        return orderList.stream().filter(o -> o.isStatus()).mapToInt(o -> o.getCount()).sum();
    }

    public List<FundingRepository.AfterMoodingSuccessResponseInterface> getMySuccessFundingResult(
            String accessToken) {
        int userId = tokenAuthClient.getUserId(accessToken);
        return fundingRepository.getMySuccessFundingResult(userId);
    }

    public List<FundingRepository.AfterMoodingFailureResponseInterface> getMyFailureFundingResult(
            String accessToken) {
        int userId = tokenAuthClient.getUserId(accessToken);
        return fundingRepository.getMyFailureFundingResult(userId);
    }

    public Integer getFundingId(List<Integer> fundingIdList) {
        LocalDate recentDate = LocalDate.now();
        int fundingId = fundingIdList.get(0);

        for (int i = 0; i < fundingIdList.size(); i++) {
            Funding funding =
                    fundingRepository
                            .findById(fundingIdList.get(i))
                            .orElseThrow(() -> new BadRequestException(FUNDING_NOT_FOUND));

            LocalDate tmpDate = funding.getDate();
            if (i == 0) {
                recentDate = tmpDate;
            } else {
                if (tmpDate.isBefore(recentDate)) {
                    recentDate = tmpDate;
                    fundingId = fundingIdList.get(i);
                }
            }
        }

        return fundingId;
    }
}
