package com.ssafy.funding.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorReason {
    private final String code;
    private final String reason;
}
