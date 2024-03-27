package com.ssafy.funding.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FundingDescResponse {

    @NotNull
    @Schema(description = "펀딩 아이디")
    private int fundingId;

    @Schema(description = "펀딩 시작일")
    private String startAt;

    @Schema(description = "상영일")
    private String date;

    @Schema(description = "시작 시간")
    private String time;

    @Schema(description = "가격")
    private int price;

    @Schema(description = "펀딩 성공 횟수")
    private int success;
}
