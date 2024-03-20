package com.ssafy.funding.exception;

import com.ssafy.funding.exception.global.CodeException;
import com.ssafy.funding.exception.global.CustomExceptionStatus;

public class FundingQuantityLackException extends CodeException {

    public static final CodeException EXCEPTION = new FundingQuantityLackException();

    private FundingQuantityLackException() {
        super(CustomExceptionStatus.FUNDING_QUANTITY_LACK);
    }
}
