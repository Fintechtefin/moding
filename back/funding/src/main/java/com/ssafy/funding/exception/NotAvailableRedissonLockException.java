package com.ssafy.funding.exception;

import com.ssafy.funding.exception.global.CodeException;
import com.ssafy.funding.exception.global.CustomExceptionStatus;

public class NotAvailableRedissonLockException extends CodeException {
    public static final CodeException EXCEPTION = new NotAvailableRedissonLockException();

    private NotAvailableRedissonLockException() {
        super(CustomExceptionStatus.NOT_AVAILABLE_REDISSON_LOCK);
    }
}
