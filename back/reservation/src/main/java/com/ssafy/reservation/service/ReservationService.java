package com.ssafy.reservation.service;

import static com.ssafy.reservation.exception.global.CustomExceptionStatus.NOT_CANCELED_RESERVATION_ID;
import static com.ssafy.reservation.exception.global.CustomExceptionStatus.NOT_FOUND_RSERVATION_ID;

import com.ssafy.reservation.controller.fegin.ReservationClient;
import com.ssafy.reservation.controller.fegin.TokenAuthClient;
import com.ssafy.reservation.domain.Reservation;
import com.ssafy.reservation.dto.Seat;
import com.ssafy.reservation.dto.request.MakeReservationRequest;
import com.ssafy.reservation.dto.response.FundingInfoResponse;
import com.ssafy.reservation.dto.response.TicketInfoResponse;
import com.ssafy.reservation.exception.BadRequestException;
import com.ssafy.reservation.exception.global.CustomExceptionStatus;
import com.ssafy.reservation.repository.ReservationRepository;
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
    private final ReservationClient reservationClient;
    private final TokenAuthClient tokenAuthClient;
    private final int completedStatus = 1;

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
        List<Reservation> reservationList =
                reservationRepository.findListByFundingIdAndStatus(
                        makeReservationRequest.getFundingId(), 1);

        List<Seat> requestSeat = makeReservationRequest.getSeats().getSeat();

        List<Seat> seatList =
                reservationList.stream()
                        .map(reservation -> reservation.getSeats().getSeat())
                        .flatMap(inner -> inner.stream())
                        .collect(Collectors.toList());

        Optional<Seat> result =
                requestSeat.stream()
                        .filter(seat -> seatList.stream().anyMatch(Predicate.isEqual(seat)))
                        .findFirst();

        if (result.isPresent()) {
            throw new BadRequestException(CustomExceptionStatus.RESERVATION_SEAT);
        }
    }

    // 좌석 예매 내역 db 저장
    @Transactional
    public int makeReservation(MakeReservationRequest makeReservationRequest, int userId) {
        Reservation reservation = Reservation.of(makeReservationRequest, userId, completedStatus);
        reservationRepository.save(reservation);
        int reservationId = reservation.getId();
        return reservationId;
    }

    public TicketInfoResponse getTicket(String accessToken, Integer reservationId) {
        Reservation reservation =
                reservationRepository
                        .findById(reservationId)
                        .orElseThrow(() -> new BadRequestException(NOT_FOUND_RSERVATION_ID));

        if (reservation.getStatus() == 0) {
            throw new BadRequestException(CustomExceptionStatus.CANCELED_RSERVATION_ID);
        }

        FundingInfoResponse fundingInfoResponse =
                reservationClient.getTicketInfo(accessToken, reservation.getFundingId());

        TicketInfoResponse ticketInfoResponse =
                TicketInfoResponse.of(fundingInfoResponse, reservation);

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
    public void cancelReservation(final Integer reservationId) {
        final Reservation reservation =
                reservationRepository
                        .findById(reservationId)
                        .orElseThrow(() -> new BadRequestException(NOT_FOUND_RSERVATION_ID));

        if (reservation.getStatus() == 0) {
            throw new BadRequestException(NOT_CANCELED_RESERVATION_ID);
        }

        reservation.changeStatus();
        reservationRepository.save(reservation);
    }
}
