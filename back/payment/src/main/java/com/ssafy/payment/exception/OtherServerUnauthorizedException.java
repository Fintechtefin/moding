package com.ssafy.payment.exception;

import com.ssafy.common.exception.CodeException;

public class OtherServerUnauthorizedException extends CodeException {
    public static final CodeException EXCEPTION = new OtherServerUnauthorizedException();

    private OtherServerUnauthorizedException() {
        super(CustomExceptionStatus.OTHER_SERVER_UNAUTHORIZED);
    }
}
