package com.ssafy.funding.domain.validator;

import com.ssafy.funding.common.annotation.Validator;
import com.ssafy.funding.domain.Funding;
import com.ssafy.funding.domain.Order;
import com.ssafy.funding.exception.NotOwnerOrderException;
import com.ssafy.funding.repository.FundingRepository;
import lombok.RequiredArgsConstructor;

/**
 * 주문 영역에 관한 검증용 메서드의 집합 레퍼런스 : https://www.youtube.com/watch?v=dJ5C4qRqAgA&t=4691s 1시 19분쯤 다른객체의
 * 참조가 필요한 상황이므로 밸리데이터를 다른 객체로 뺌.
 */
@Validator
@RequiredArgsConstructor
public class OrderValidator {

    private final FundingRepository fundingRepository;

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
        order.getOrderFundings().stream()
                .forEach(o -> funding.validEnoughQuantity(o.getFunding().getPeopleCount()));
    }
}
