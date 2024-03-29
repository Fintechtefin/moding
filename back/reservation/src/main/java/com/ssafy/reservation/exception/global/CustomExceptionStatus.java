package com.ssafy.reservation.exception.global;

import com.ssafy.reservation.dto.ErrorReason;

public enum CustomExceptionStatus implements BaseErrorCode {
    INVALID_REQUEST("BadRequest_400_1", "올바르지 않은 요청입니다."),
    INTERNAL_SEVER_ERROR("InternalServer_500_1", "서버 에러가 발생하였습니다. 관리자에게 문의해 주세요."),
    REQUEST_ERROR("NotValidInput_400_2", "입력 값을 확인해 주세요."),

    RESERVATION_SEAT("Reservation_400_1", "이미 예매 완료된 좌석입니다."),
    NOT_PAYMENTS_USER("Reservation_400_2", "해당 펀딩에 결제하지 않은 사용자입니다."),
    NOT_FOUND_RSERVATION_ID("Reservation_400_3", "존재하지 않는 예매번호입니다."),
    CANCELED_RSERVATION_ID("Reservation_400_4", "취소된 예매 내역입니다."),
    NOT_CANCELED_RESERVATION_ID("Reservation_400_5", "이미 취소된 예매 내역입니다.");

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
