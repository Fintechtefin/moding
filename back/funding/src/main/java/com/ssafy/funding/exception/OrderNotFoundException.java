package com.ssafy.funding.exception;

import com.ssafy.funding.exception.global.CodeException;
import com.ssafy.funding.exception.global.CustomExceptionStatus;

public class OrderNotFoundException extends CodeException {
    public static final CodeException EXCEPTION = new OrderNotFoundException();

    private OrderNotFoundException() {
        super(CustomExceptionStatus.ORDER_NOT_FOUND);
    }
}
