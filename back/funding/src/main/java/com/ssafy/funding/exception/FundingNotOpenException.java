package com.ssafy.funding.exception;

import com.ssafy.funding.exception.global.CodeException;
import com.ssafy.funding.exception.global.CustomExceptionStatus;

public class FundingNotOpenException extends CodeException {

    public static final CodeException EXCEPTION = new FundingNotOpenException();

    private FundingNotOpenException() {
        super(CustomExceptionStatus.FUNDING_NOT_OPEN);
    }
}
