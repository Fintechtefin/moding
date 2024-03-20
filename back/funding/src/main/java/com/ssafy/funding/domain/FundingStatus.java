package com.ssafy.funding.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FundingStatus {
    // 참여 가능한 펀딩
    OPEN("OPEN", "오픈"),
    CLOSED("CLOSED", "종료"),
    WAITING("WAITING", "대기");

    private String value;

    @JsonValue private final String kr;
}
