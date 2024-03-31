package com.ssafy.toss.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorReason {
    private final String code;
    private final String reason;
}
