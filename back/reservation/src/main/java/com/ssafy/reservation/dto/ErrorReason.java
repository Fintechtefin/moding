package com.ssafy.reservation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorReason {
    private final String code;
    private final String reason;
}
