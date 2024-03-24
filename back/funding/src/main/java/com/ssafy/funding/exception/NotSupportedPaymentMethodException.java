package com.ssafy.funding.exception;

import com.ssafy.funding.exception.global.CodeException;
import com.ssafy.funding.exception.global.CustomExceptionStatus;

public class NotSupportedPaymentMethodException extends CodeException {
    public static final CodeException EXCEPTION = new NotSupportedPaymentMethodException();

    private NotSupportedPaymentMethodException() {
        super(CustomExceptionStatus.PAYMENT_NOT_SUPPORTED_METHOD);
    }
}
