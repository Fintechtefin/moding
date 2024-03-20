package com.ssafy.funding.exception;

import com.ssafy.funding.exception.global.CodeException;
import com.ssafy.funding.exception.global.CustomExceptionStatus;

public class PaymentsEnumNotMatchException extends CodeException {
    public static final CodeException EXCEPTION = new PaymentsEnumNotMatchException();

    private PaymentsEnumNotMatchException() {
        super(CustomExceptionStatus.TOSS_PAYMENTS_ENUM_NOT_MATCH);
    }
}
