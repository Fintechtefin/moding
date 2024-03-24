package com.ssafy.funding.exception;

import com.ssafy.funding.exception.global.CodeException;
import com.ssafy.funding.exception.global.CustomExceptionStatus;

public class InvalidOrderException extends CodeException {

    public static final CodeException EXCEPTION = new InvalidOrderException();

    private InvalidOrderException() {
        super(CustomExceptionStatus.ORDER_NOT_VALID);
    }
}
