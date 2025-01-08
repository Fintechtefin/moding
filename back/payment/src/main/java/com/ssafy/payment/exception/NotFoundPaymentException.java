package com.ssafy.payment.exception;

import com.ssafy.common.exception.CodeException;

public class NotFoundPaymentException extends CodeException {
    public static final CodeException EXCEPTION = new NotFoundPaymentException();

    private NotFoundPaymentException() {
        super(CustomExceptionStatus.NOT_FOUND_PAYMENT);
    }
}
