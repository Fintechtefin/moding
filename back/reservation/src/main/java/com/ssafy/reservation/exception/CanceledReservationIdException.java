package com.ssafy.reservation.exception;

import com.ssafy.reservation.exception.global.CodeException;
import com.ssafy.reservation.exception.global.CustomExceptionStatus;

public class CanceledReservationIdException extends CodeException {
    public static final CodeException EXCEPTION = new CanceledReservationIdException();

    private CanceledReservationIdException() {
        super(CustomExceptionStatus.CANCELED_RSERVATION_ID);
    }
}
