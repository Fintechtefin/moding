package com.ssafy.payment.exception;

import com.ssafy.payment.dto.ErrorReason;

public interface BaseErrorCode {
    public ErrorReason getErrorReason();
}
