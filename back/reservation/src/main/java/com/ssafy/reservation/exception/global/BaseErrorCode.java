package com.ssafy.reservation.exception.global;

import com.ssafy.reservation.dto.ErrorReason;

public interface BaseErrorCode {
    public ErrorReason getErrorReason();
}
