package com.ssafy.funding.exception;

import com.ssafy.funding.exception.global.CodeException;
import com.ssafy.funding.exception.global.CustomExceptionStatus;

public class NotSupportedOrderMethodException extends CodeException {

    public static final CodeException EXCEPTION = new NotSupportedOrderMethodException();

    private NotSupportedOrderMethodException() {
        super(CustomExceptionStatus.ORDER_NOT_SUPPORTED_METHOD);
    }
}
