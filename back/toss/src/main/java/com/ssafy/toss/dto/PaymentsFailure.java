package com.ssafy.toss.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentsFailure {
    private String code;
    private String message;
}
