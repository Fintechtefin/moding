package com.ssafy.funding.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class CreateOrderRequest {
    @Nullable
    @Schema(nullable = false)
    private Integer fundingId;

    @NotNull
    @Schema(nullable = false)
    private Integer fundingCount;
}
