package com.ssafy.funding.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FundingStatus {
    // 참여 가능한 펀딩
    NONE("NONE", "무딩 준비 중"),
    READY_TO_OPEN("READY_TO_OPEN", "무딩 예정"),
    OPEN("OPEN", "무딩 중"),
    READY_TO_RESERVATION("READY_TO_RESERVATION", "예매 예정"),
    RESERVATION("RESERVATION", "예매 진행"),
    CLOSED("CLOSED", "무딩 종료");

    private String value;

    @JsonValue private final String kr;
}
