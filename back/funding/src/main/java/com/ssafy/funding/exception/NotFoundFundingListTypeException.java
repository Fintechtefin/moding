package com.ssafy.funding.exception;

import com.ssafy.funding.exception.global.CodeException;
import com.ssafy.funding.exception.global.CustomExceptionStatus;

public class NotFoundFundingListTypeException extends CodeException {
    public static final CodeException EXCEPTION = new NotFoundFundingListTypeException();

    private NotFoundFundingListTypeException() {
        super(CustomExceptionStatus.FUNDING_LIST_TYPE_NOT_FOUND);
    }
}
