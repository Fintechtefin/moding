package com.ssafy.funding.exception;

import com.ssafy.funding.exception.global.CodeException;
import com.ssafy.funding.exception.global.CustomExceptionStatus;

public class BadLockIdentifierException extends CodeException {

    public static final CodeException EXCEPTION = new BadLockIdentifierException();

    private BadLockIdentifierException() {
        super(CustomExceptionStatus.BAD_LOCK_IDENTIFIER);
    }
}
