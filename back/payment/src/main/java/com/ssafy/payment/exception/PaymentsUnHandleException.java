package com.ssafy.payment.exception;

import com.ssafy.common.exception.CodeException;

public class PaymentsUnHandleException extends CodeException {
    public static final CodeException EXCEPTION = new PaymentsUnHandleException();

    private PaymentsUnHandleException() {
        super(CustomExceptionStatus.TOSS_PAYMENTS_UNHANDLED);
    }
}
