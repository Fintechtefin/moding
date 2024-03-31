package com.ssafy.toss.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomExceptionStatus implements BaseErrorCode {
    TOSS_PAYMENTS_ENUM_NOT_MATCH("INFRA_500_1", "토스페이먼츠 이넘값 관련 매칭 안된 문제입니다.");

    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).build();
    }
}
