package com.ssafy.reservation.exception;

import com.ssafy.reservation.exception.global.CodeException;
import com.ssafy.reservation.exception.global.CustomExceptionStatus;

public class ReservationUserException extends CodeException {
    public static final CodeException EXCEPTION = new ReservationUserException();

    private ReservationUserException() {
        super(CustomExceptionStatus.RESERVATION_USER);
    }
}
