package com.ssafy.payment.exception;

public class PaymentsUnHandleException extends CodeException {
    public static final CodeException EXCEPTION = new PaymentsUnHandleException();

    private PaymentsUnHandleException() {
        super(CustomExceptionStatus.TOSS_PAYMENTS_UNHANDLED);
    }
}
