package com.ssafy.funding.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JoinFundingListResponse {

    private final List<JoinFundingResponse> joinFundingResponseList;

    private final boolean hasNext;

    public static JoinFundingListResponse of(List<JoinFundingResponse> joinFundingResponse) {
        return JoinFundingListResponse.builder()
                .joinFundingResponseList(joinFundingResponse)
                .build();
    }
}
