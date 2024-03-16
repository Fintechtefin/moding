package com.ssafy.payment.exception;

public class OtherServerForbiddenException extends CodeException {
    public static final CodeException EXCEPTION = new OtherServerForbiddenException();

    private OtherServerForbiddenException() {
        super(CustomExceptionStatus.OTHER_SERVER_FORBIDDEN);
    }
}
