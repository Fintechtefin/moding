package com.ssafy.funding.exception;

import com.ssafy.funding.exception.global.CodeException;
import com.ssafy.funding.exception.global.CustomExceptionStatus;

public class NotOwnerOrderException extends CodeException {
    public static final CodeException EXCEPTION = new NotOwnerOrderException();

    private NotOwnerOrderException() {
        super(CustomExceptionStatus.ORDER_NOT_MINE);
    }
}
