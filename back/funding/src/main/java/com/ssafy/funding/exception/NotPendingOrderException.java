package com.ssafy.funding.exception;

import com.ssafy.funding.exception.global.CodeException;
import com.ssafy.funding.exception.global.CustomExceptionStatus;

public class NotPendingOrderException extends CodeException {
    public static final CodeException EXCEPTION = new NotPendingOrderException();

    private NotPendingOrderException() {
        super(CustomExceptionStatus.ORDER_NOT_PENDING);
    }
}
