package com.ssafy.reservation.service;

import static com.ssafy.reservation.exception.global.CustomExceptionStatus.NOT_FOUND_RSERVATION_ID;

import com.ssafy.reservation.controller.fegin.ReservationClient;
import com.ssafy.reservation.controller.fegin.TokenAuthClient;
import com.ssafy.reservation.domain.Reservation;
import com.ssafy.reservation.domain.Seat;
import com.ssafy.reservation.dto.request.MakeReservationRequest;
import com.ssafy.reservation.dto.response.FundingInfoResponse;
import com.ssafy.reservation.dto.response.TicketInfoResponse;
import com.ssafy.reservation.exception.BadRequestException;
import com.ssafy.reservation.exception.global.CustomExceptionStatus;
import com.ssafy.reservation.repository.ReservationRepository;
import com.ssafy.reservation.repository.SeatRepository;
import com.ssafy.reservation.util.RedisUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;
    private final ReservationClient reservationClient;
    private final TokenAuthClient tokenAuthClient;
    private final RedisUtil redisUtil;

    public int getCurrentUserId(String accessToken) {
        return tokenAuthClient.getUserId(accessToken);
    }

    public String getSeatList(int userId, int fundingId) {
        checkPaymentUser(fundingId, userId);
        if (redisUtil.getData("seat_funding_" + fundingId) != null) {
            return redisUtil.getData("seat_funding_" + fundingId);
        }
        return null;
    }

    public void checkPaymentUser(int fundingId, int userId) {
        if (!reservationClient.checkPaymentUser(fundingId, userId)) {
            throw new BadRequestException(CustomExceptionStatus.NOT_PAYMENTS_USER);
        }
    }

    public void checkReservation(int fundingId, int userId) {
        if (reservationRepository.findByUserIdAndFundingIdAndStatus(userId, fundingId, 1) != null) {
            throw new BadRequestException(CustomExceptionStatus.RESERVATION_USER);
        }
    }

    @Transactional
    public int checkSeat(MakeReservationRequest makeReservationRequest, int userId) {
        if (redisUtil.getData("seat_funding_" + makeReservationRequest.getFundingId()) != null) {
            String seatRedis =
                    redisUtil.getData("seat_funding_" + makeReservationRequest.getFundingId());
            List<String> seatList = new ArrayList<>();
            char[] arrCh = seatRedis.toCharArray();
            String seat = "";
            for (char ch : arrCh) {
                if (Character.isLetter(ch)) {
                    if (!seat.isEmpty()) {
                        seatList.add(seat);
                        seat = "";
                    } else {
                        seat = Character.toString(ch);
                    }
                } else {
                    seat = seat.concat(Character.toString(ch));
                    seatList.add(seat);
                    seat = "";
                }
            }

            if (!seat.isEmpty()) {
                seatList.add(seat);
            }

            Optional<String> isSeat =
                    makeReservationRequest.getPosition().stream()
                            .filter(s -> seatList.stream().anyMatch(Predicate.isEqual(s)))
                            .findFirst();

            if (isSeat.isPresent()) {
                throw new BadRequestException(CustomExceptionStatus.RESERVATION_SEAT);
            }
        }

        for (String seat : makeReservationRequest.getPosition()) {
            Seat seats =
                    seatRepository.findByFundingIdAndPosition(
                            makeReservationRequest.getFundingId(), seat);
            if (seats != null && seats.getReservation().getStatus() != 0) {
                throw new BadRequestException(CustomExceptionStatus.RESERVATION_SEAT);
            }
        }

        Reservation reservation = Reservation.of(makeReservationRequest, userId, 1);
        reservationRepository.save(reservation);

        makeReservationRequest.getPosition().stream()
                .forEach(
                        s ->
                                seatRepository.save(
                                        Seat.of(
                                                s,
                                                makeReservationRequest.getFundingId(),
                                                reservation)));

        String key = "seat_funding_" + makeReservationRequest.getFundingId();
        String redisValue = "";
        if (redisUtil.getData(key) != null) {
            redisValue = redisUtil.getData(key);
        }
        for (String position : makeReservationRequest.getPosition()) {
            redisValue = redisValue.concat(position);
        }
        redisUtil.setData(key, redisValue);

        return reservation.getId();
    }

    //    // 좌석 예매 내역 db 저장
    //    @Transactional
    //    public int makeReservation(MakeReservationRequest makeReservationRequest, int userId) {
    //        Reservation reservation = Reservation.of(makeReservationRequest, userId, 1);
    //        reservationRepository.save(reservation);
    //
    //        makeReservationRequest.getPosition().stream()
    //                .forEach(
    //                        s ->
    //                                seatRepository.save(
    //                                        Seat.of(
    //                                                s,
    //                                                makeReservationRequest.getFundingId(),
    //                                                reservation)));
    //
    //        String key = "seat_funding_" + makeReservationRequest.getFundingId();
    //        String redisValue = "";
    //        if(redisUtil.getData(key) != null){
    //            redisValue = redisUtil.getData(key);
    //        }
    //        for(String position : makeReservationRequest.getPosition()){
    //            redisValue = redisValue.concat(position);
    //        }
    //        redisUtil.setData(key, redisValue);
    //
    //        return reservation.getId();
    //    }

    public TicketInfoResponse getTicket(String accessToken, int reservationId) {
        Reservation reservation =
                reservationRepository
                        .findById(reservationId)
                        .orElseThrow(() -> new BadRequestException(NOT_FOUND_RSERVATION_ID));

        if (reservation.getStatus() == 0) {
            throw new BadRequestException(CustomExceptionStatus.CANCELED_RSERVATION_ID);
        }

        FundingInfoResponse fundingInfoResponse =
                reservationClient.getTicketInfo(accessToken, reservation.getFundingId());

        List<String> seats =
                (seatRepository.findByReservationIdAndFundingId(
                                reservationId, reservation.getFundingId()))
                        .stream()
                                .map(seatPositionInfo -> seatPositionInfo.getPosition())
                                .collect(Collectors.toList());

        TicketInfoResponse ticketInfoResponse =
                TicketInfoResponse.of(seats, fundingInfoResponse, reservation);

        return ticketInfoResponse;
    }

    public TicketInfoResponse getRecentTicket(String accessToken, int userId) {

        List<ReservationRepository.ReservationInfo> reservationInfoList =
                reservationRepository.findByUserIdAndStatus(userId, 1);

        if (reservationInfoList.size() == 0) {
            return null;
        }

        List<Integer> fundingList = new ArrayList<>();
        for (int i = 0; i < reservationInfoList.size(); i++) {
            fundingList.add(reservationInfoList.get(i).getFundingId());
        }

        int fundingId = reservationClient.getFundingId(fundingList);

        int reservationId =
                reservationRepository
                        .findByUserIdAndFundingIdAndStatus(userId, fundingId, 1)
                        .getId();

        return getTicket(accessToken, reservationId);
    }

    @Transactional
    public void cancelReservation(final int reservationId, final int userId) {
        Reservation reservation =
                reservationRepository.findByIdAndUserIdAndStatus(reservationId, userId, 1);

        if (reservation == null) {
            throw new BadRequestException(NOT_FOUND_RSERVATION_ID);
        }

        int fundingId = reservation.getFundingId();

        reservation.changeStatus();
        reservationRepository.save(reservation);

        // redis cache overwrite
        List<Reservation> reservationList =
                reservationRepository.findListByFundingIdAndStatus(fundingId, 1);
        if (reservationList == null) {
            throw new BadRequestException(NOT_FOUND_RSERVATION_ID);
        }
        String value = "";
        for (Reservation res : reservationList) {
            value = value.concat(seatRepository.findByReservationId(res.getId()).getPosition());
        }

        redisUtil.setData("seat_funding_" + fundingId, value);
    }
}
