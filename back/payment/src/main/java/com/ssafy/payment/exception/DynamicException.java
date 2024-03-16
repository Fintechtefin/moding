package com.ssafy.payment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DynamicException extends RuntimeException {
    private final String code;
    private final String reason;
}
