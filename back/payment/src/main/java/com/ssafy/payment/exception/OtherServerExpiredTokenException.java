package com.ssafy.payment.exception;

import com.ssafy.common.exception.CodeException;

public class OtherServerExpiredTokenException extends CodeException {
    public static final CodeException EXCEPTION = new OtherServerExpiredTokenException();

    private OtherServerExpiredTokenException() {
        super(CustomExceptionStatus.OTHER_SERVER_EXPIRED_TOKEN);
    }
}
