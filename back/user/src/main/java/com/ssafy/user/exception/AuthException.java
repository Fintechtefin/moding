package com.ssafy.user.exception;

import com.ssafy.user.common.CustomExceptionStatus;
import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

    private final String code;
    private final String message;

    public AuthException(final CustomExceptionStatus customExceptionStatus) {
        this.code = customExceptionStatus.getCode();
        this.message = customExceptionStatus.getMessage();
    }
}
