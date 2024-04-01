package com.ssafy.reservation.service;

import static com.ssafy.reservation.exception.global.CustomExceptionStatus.NOT_CANCELED_RESERVATION_ID;
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
import java.util.ArrayList;
import java.util.List;
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

    public int getCurrentUserId(String accessToken) {
        return tokenAuthClient.getUserId(accessToken);
    }

    public void checkReservation(int fundingId, int userId) {
        if (reservationRepository.findByUserIdAndFundingIdAndStatus(userId, fundingId, 1) != null) {
            throw new BadRequestException(CustomExceptionStatus.RESERVATION_USER);
        }
    }

    public void checkPaymentUser(int fundingId, int userId) {
        if (!reservationClient.checkPaymentUser(fundingId, userId)) {
            throw new BadRequestException(CustomExceptionStatus.NOT_PAYMENTS_USER);
        }
    }

    public void checkSeat(MakeReservationRequest makeReservationRequest) {
        // 1. db에서 가져오기
        for (String seat : makeReservationRequest.getPosition()) {
            Seat seats =
                    seatRepository.findByFundingIdAndPosition(
                            makeReservationRequest.getFundingId(), seat);
            if (seats != null && seats.getReservation().getStatus() != 0) {
                throw new BadRequestException(CustomExceptionStatus.RESERVATION_SEAT);
            }
        }
    }

    // 좌석 예매 내역 db 저장
    @Transactional
    public int makeReservation(MakeReservationRequest makeReservationRequest, int userId) {
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

        int reservationId = reservation.getId();
        return reservationId;
    }

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
                        .getFundingId();

        return getTicket(accessToken, reservationId);
    }

    @Transactional
    public void cancelReservation(final int reservationId, final int userId) {
        Reservation reservation =
                reservationRepository.findByIdAndUserIdAndStatus(reservationId, userId, 1);
        //                        .orElseThrow(()->new
        // BadRequestException(NOT_FOUND_RSERVATION_ID));

        if (reservation == null) {
            throw new BadRequestException(NOT_FOUND_RSERVATION_ID);
        }

        if (reservation.getStatus() == 0) {
            throw new BadRequestException(NOT_CANCELED_RESERVATION_ID);
        }

        reservation.changeStatus();
        reservationRepository.save(reservation);
    }
}
