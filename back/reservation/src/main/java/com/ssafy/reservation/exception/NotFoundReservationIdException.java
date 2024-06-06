package com.ssafy.reservation.exception;

import com.ssafy.reservation.exception.global.CodeException;
import com.ssafy.reservation.exception.global.CustomExceptionStatus;

public class NotFoundReservationIdException extends CodeException {
    public static final CodeException EXCEPTION = new NotFoundReservationIdException();

    private NotFoundReservationIdException() {
        super(CustomExceptionStatus.NOT_FOUND_RSERVATION_ID);
    }
}
