package com.ssafy.toss.exception;

public class PaymentsEnumNotMatchException extends CodeException {
    public static final CodeException EXCEPTION = new PaymentsEnumNotMatchException();

    private PaymentsEnumNotMatchException() {
        super(CustomExceptionStatus.TOSS_PAYMENTS_ENUM_NOT_MATCH);
    }
}
