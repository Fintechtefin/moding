package com.ssafy.funding.exception.global;

import com.ssafy.funding.common.ErrorReason;

public enum CustomExceptionStatus implements BaseErrorCode {
    INVALID_REQUEST("BadRequest_400_1", "올바르지 않은 요청입니다."),
    INTERNAL_SEVER_ERROR("InternalServer_500_1", "서버 에러가 발생하였습니다. 관리자에게 문의해 주세요."),
    REQUEST_ERROR("NotValidInput_400_2", "입력 값을 확인해 주세요."),

    ORDER_NOT_MINE("Order_400_1", "본인의 주문이 아닙니다."),
    // 토스 결제 금액과 , 주문금액이 다를 때 등 올바르지 않은 주문 상태를 가질 때 발생하는 오류
    ORDER_NOT_VALID("Order_400_2", "올바르지 않은 주문입니다."),
    ORDER_NOT_PENDING("Order_400_3", "결제,승인 대기중인 주문이 아닙니다."),
    ORDER_NOT_SUPPORTED_METHOD("Order_400_4", "지원하지 않는 방식의 주문입니다."),
    ORDER_CANNOT_CANCEL("Order_400_5", "주문을 취소할 수 없는 상태입니다."),
    ORDER_CANNOT_REFUND("Order_400_6", "주문을 환불할 수 없는 상태입니다."),

    ORDER_NOT_APPROVAL("Order_400_7", "승인 주문이 아닙니다."),
    ORDER_NOT_PAYMENT("Order_400_8", "결제 주문이 아닙니다."),

    ORDER_NOT_REFUND_DATE("Order_400_9", "환불을 할 수 있는 기한을 지났습니다."),
    ORDER_FORBIDDEN("Order_400_10", "한 펀딩은 한 번만 주문할 수 있습니다. 다시 주문하고 싶으면 이전 주문을 취소하세요."),
    ORDER_NOT_FOUND("Order_404_1", "주문을 찾을 수 없습니다."),
    ORDER_FUNDING_FOUND("Order_404_2", "주문 라인을 찾을 수 없습니다."),

    // ORDER_NOT_FREE("Order_400_10", "무료 주문이 아닙니다."),

    // ORDER_LESS_THAN_MINIMUM("Order_400_11", "최소 결제금액인 1000원보다 낮은 주문입니다."),
    // 한 장바구니엔 관련된 한 아이템만 올수 있음
    // ORDER_INVALID_ITEM_KIND_POLICY("Order_400_12", "장바구니에 아이템을 담는 정책을 위반하였습니다."),
    ORDER_OPTION_CHANGED("Order_400_13", "주문 과정중 아이템의 옵션이 변화했습니다."),
    CAN_NOT_DELETED_USER_APPROVE("Order_400_14", "유저가 탈퇴를 했습니다."),
    APPROVE_WAITING_PURCHASE_LIMIT(
            "Order_400_15",
            "승인 대기중인 주문으로 인해 티켓 최대 구매 가능 횟수를 넘겼습니다." + "이미 신청한 주문이 승인 될 때까지 기다려주세요."),
    ORDER_CANNOT_REFUSE("Order_400_16", "승인 대기중인 주문을 거절할 수 없는 상태입니다."),

    TOSS_PAYMENTS_ENUM_NOT_MATCH("INFRA_500_1", "토스페이먼츠 이넘값 관련 매칭 안된 문제입니다."),

    PAYMENT_NOT_SUPPORTED_METHOD("PAYMENTS_CREATE_NOT_SUPPORTED_METHOD", "지원되지 않는 결제 수단입니다."),

    MOVIE_NOT_FOUND("MOVIE_404_1", "영화를 찾을 수 없습니다."),

    FUNDING_NOT_FOUND("FUNDING_404_1", "펀딩을 찾을 수 없습니다."),
    FUNDING_TIME_IS_PASSED("FUNDING_400_6", "펀딩 시작시간이 지나 예매를 할 수 없습니다."),
    FUNDING_NOT_OPEN("FUNDING_400_5", "아직 오픈되지 않은 펀딩에는 접근할 수 없습니다."),
    FUNDING_QUANTITY_LACK("Ticket_Item_400_1", "펀딩 모집 인원을 초과했습니다."),

    NOT_AVAILABLE_REDISSON_LOCK("Redisson_500_1", "can not get redisson lock"),
    BAD_LOCK_IDENTIFIER("AOP_500_1", "락의 키값이 잘못 세팅 되었습니다");

    private final String code;
    private final String message;

    private CustomExceptionStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(message).code(code).build();
    }
}
