package com.ssafy.reservation.exception;

import com.ssafy.reservation.exception.global.CodeException;
import com.ssafy.reservation.exception.global.CustomExceptionStatus;

public class ReservationSeatException extends CodeException {
    public static final CodeException EXCEPTION = new ReservationSeatException();

    private ReservationSeatException() {
        super(CustomExceptionStatus.RESERVATION_SEAT);
    }
}
