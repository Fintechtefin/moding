package com.ssafy.common.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorReason {
    private final String code;
    private final String reason;
}