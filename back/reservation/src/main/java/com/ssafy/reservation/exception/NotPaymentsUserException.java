package com.ssafy.reservation.exception;

import com.ssafy.reservation.exception.global.CodeException;
import com.ssafy.reservation.exception.global.CustomExceptionStatus;

public class NotPaymentsUserException extends CodeException {
    public static final CodeException EXCEPTION = new NotPaymentsUserException();

    private NotPaymentsUserException() {
        super(CustomExceptionStatus.NOT_PAYMENTS_USER);
    }
}
