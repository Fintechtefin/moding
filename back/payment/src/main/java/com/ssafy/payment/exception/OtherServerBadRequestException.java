package com.ssafy.payment.exception;

public class OtherServerBadRequestException extends CodeException {
    public static final CodeException EXCEPTION = new OtherServerBadRequestException();

    private OtherServerBadRequestException() {
        super(CustomExceptionStatus.OTHER_SERVER_BAD_REQUEST);
    }
}
