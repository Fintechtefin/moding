package com.ssafy.reservation.exception;

import com.ssafy.reservation.exception.global.CodeException;
import com.ssafy.reservation.exception.global.CustomExceptionStatus;

public class NotCanceledReservationId extends CodeException {
    public static final CodeException EXCEPTION = new NotCanceledReservationId();

    private NotCanceledReservationId() {
        super(CustomExceptionStatus.NOT_CANCELED_RESERVATION_ID);
    }
}
