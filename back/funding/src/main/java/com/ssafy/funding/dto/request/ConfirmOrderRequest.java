package com.ssafy.funding.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmOrderRequest {
    @Nullable
    @Schema(nullable = false)
    private String paymentKey;

    @Nullable
    @Schema(nullable = false)
    private Long amount;

    @Nullable
    @Schema(nullable = false)
    private Integer fundingId;

    @NotNull
    @Schema(nullable = false)
    private Integer fundingCount;

    @NotNull
    @Schema(nullable = false)
    private String method; // 결제 수단
}
