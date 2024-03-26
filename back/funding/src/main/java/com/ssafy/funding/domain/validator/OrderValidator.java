package com.ssafy.funding.domain.validator;

import static com.ssafy.funding.exception.global.CustomExceptionStatus.ORDER_NOT_REFUND_DATE;

import com.ssafy.funding.common.annotation.Validator;
import com.ssafy.funding.domain.Funding;
import com.ssafy.funding.domain.Order;
import com.ssafy.funding.dto.Money;
import com.ssafy.funding.exception.*;
import com.ssafy.funding.repository.FundingRepository;
import com.ssafy.funding.repository.MovieRepository;
import lombok.RequiredArgsConstructor;

/**
 * 주문 영역에 관한 검증용 메서드의 집합 레퍼런스 : https://www.youtube.com/watch?v=dJ5C4qRqAgA&t=4691s 1시 19분쯤 다른객체의
 * 참조가 필요한 상황이므로 밸리데이터를 다른 객체로 뺌.
 */
@Validator
@RequiredArgsConstructor
public class OrderValidator {

    private final FundingRepository fundingRepository;
    private final MovieRepository movieRepository;

    /** 주문에대한 주인인지 검증합니다. */
    public void validOwner(Order order, Integer currentUserId) {
        if (!order.getUserId().equals(currentUserId)) {
            throw NotOwnerOrderException.EXCEPTION;
        }
    }

    /** 주문을 생성할 수 있는지에 대한검증 */
    public void validCanCreate(Order order, Funding funding) {
        // 펀딩이 열려있는 상태인지
        validFundingIsOpen(funding);
        // 재고가 충분히 있는지 ( 추후 좌석 예약을 하면서도 2차로 검증함 )
        validFundingStockEnough(order, funding);
    }

    /** 주문을 완료할 수 있는지에 대한 공통검증 */
    public void validCanDone(Order order, Funding funding) {
        // 펀딩이 열려있는 상태인지
        validFundingIsOpen(funding);
        // 펀딩 예매 가능 시간이 아직 안지났는지
        validFundingTime(funding);
        // 재고가 충분히 있는지 ( 추후 좌석 예약을 하면서도 2차로 검증함 )
        validFundingStockEnough(order, funding);
    }

    /** 펀딩이 열려있는 상태인지 */
    public void validFundingIsOpen(Funding funding) {
        funding.validateNotOpenStatus();
    }

    /** 티켓 예매 가능 시간이 아직 안지났는지. */
    public void validFundingTime(Funding funding) {
        funding.validateFundingTime();
    }

    /** 아이템의 재고가 충분한지 확인합니다. */
    public void validFundingStockEnough(Order order, Funding funding) {
        funding.validEnoughQuantity(order.getCount());
    }

    /** 주문 방식이 결제 방식인지 검증합니다. */
    public void validMethodIsPaymentOrder(Order order) {
        if (!isMethodPayment(order)) {
            throw NotPaymentOrderException.EXCEPTION;
        }
    }

    /** 결제대금과,요청금액의 비교를 통해 정상적인 주문인지 검증합니다. */
    public void validAmountIsSameAsRequest(Order order, Money requestAmount) {
        if (!order.getTotalPaymentPrice().equals(requestAmount)) {
            throw InvalidOrderException.EXCEPTION;
        }
    }

    private Boolean isMethodPayment(Order order) {
        return order.getOrderMethod().isPayment();
    }

    /** 환불 할 수 있는 주문인지 검증합니다. */
    //    public void validCanRefund(Order order) {
    //        Funding funding = fundingRepository.findById(order.getFunding().getId())
    //                .orElseThrow(() -> new BadRequestException(FUNDING_NOT_FOUND));
    //        movieRepository.find
    //
    //        validAvailableRefundStatus(order);
    //        validCanWithDraw(order);
    //    }

    /** 환불 가능한 상태인지 검증합니다. */
    public void validAvailableRefundStatus(Order order) {
        if (!order.getFunding().getMovie().getStatus().getValue().equals("OPEN")) {
            throw new BadRequestException(ORDER_NOT_REFUND_DATE);
        }
    }

    /*
    펀딩 시작 전 또는 종료 후라면 환불이 불가능
     */
    public void validCanWithDraw(Order order) {
        order.getFunding().validateFundingTime();
    }
}
