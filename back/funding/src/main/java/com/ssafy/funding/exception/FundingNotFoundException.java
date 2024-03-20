package com.ssafy.funding.exception;

import com.ssafy.funding.exception.global.CodeException;
import com.ssafy.funding.exception.global.CustomExceptionStatus;

public class FundingNotFoundException extends CodeException {

    public static final CodeException EXCEPTION = new FundingNotFoundException();

    private FundingNotFoundException() {
        super(CustomExceptionStatus.FUNDING_NOT_FOUND);
    }
}
