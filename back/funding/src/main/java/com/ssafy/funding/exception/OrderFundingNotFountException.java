package com.ssafy.funding.exception;

import com.ssafy.funding.exception.global.CodeException;
import com.ssafy.funding.exception.global.CustomExceptionStatus;

public class OrderFundingNotFountException extends CodeException {

    public static final CodeException EXCEPTION = new OrderFundingNotFountException();

    private OrderFundingNotFountException() {
        super(CustomExceptionStatus.ORDER_FUNDING_FOUND);
    }
}
