package com.ssafy.payment.exception;

import com.ssafy.payment.dto.ErrorReason;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomExceptionStatus implements BaseErrorCode {
    OTHER_SERVER_BAD_REQUEST("FEIGN_400_1", "Other server bad request"),
    OTHER_SERVER_UNAUTHORIZED("FEIGN_400_2", "Other server unauthorized"),
    OTHER_SERVER_FORBIDDEN("FEIGN_400_3", "Other server forbidden"),
    OTHER_SERVER_EXPIRED_TOKEN("FEIGN_400_4", "Other server expired token"),
    OTHER_SERVER_NOT_FOUND("FEIGN_400_5", "Other server not found error"),
    OTHER_SERVER_INTERNAL_SERVER_ERROR("FEIGN_400_6", "Other server internal server error"),
    NOT_AVAILABLE_REDISSON_LOCK("Redisson_500_1", "can not get redisson lock"),
    SECURITY_CONTEXT_NOT_FOUND("GLOBAL_500_2", "security context not found"),

    TOSS_PAYMENTS_UNHANDLED("PAYMENTS_INTERNAL_SERVER", "관리자에게 연락부탁드려요."),
    BAD_LOCK_IDENTIFIER("AOP_500_1", "락의 키값이 잘못 세팅 되었습니다"),
    BAD_FILE_EXTENSION("FILE_400_1", "파일 확장자가 잘못 되었습니다."),
    TOSS_PAYMENTS_ENUM_NOT_MATCH("INFRA_500_1", "토스페이먼츠 이넘값 관련 매칭 안된 문제입니다.");

    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).build();
    }
}
