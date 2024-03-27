package com.ssafy.funding.domain;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "funding_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FundingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_history_id")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id")
    private Funding funding;

    @Column(name = "funding_result", nullable = false)
    private boolean fundingResult;

    @Column(name = "funding_final_result", nullable = false)
    private boolean fundingFinalResult;

    @Column(name = "funding_result_at", nullable = false)
    private LocalDateTime fundingResultAt;
}
