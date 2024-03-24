package com.ssafy.funding.exception;

import com.ssafy.funding.exception.global.CodeException;
import com.ssafy.funding.exception.global.CustomExceptionStatus;

public class NotPaymentOrderException extends CodeException {

    public static final CodeException EXCEPTION = new NotPaymentOrderException();

    private NotPaymentOrderException() {
        super(CustomExceptionStatus.ORDER_NOT_PAYMENT);
    }
}
