package com.ssafy.funding.exception;

import com.ssafy.funding.exception.global.CodeException;
import com.ssafy.funding.exception.global.CustomExceptionStatus;

public class FundingTimeIsPassedException extends CodeException {
    public static final CodeException EXCEPTION = new FundingTimeIsPassedException();

    private FundingTimeIsPassedException() {
        super(CustomExceptionStatus.FUNDING_TIME_IS_PASSED);
    }
}
