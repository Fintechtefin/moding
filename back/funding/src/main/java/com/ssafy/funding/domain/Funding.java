package com.ssafy.funding.domain;

import static com.ssafy.funding.domain.FundingStatus.CLOSED;
import static com.ssafy.funding.domain.FundingStatus.OPEN;

import com.ssafy.funding.exception.FundingNotOpenException;
import com.ssafy.funding.exception.FundingQuantityLackException;
import com.ssafy.funding.exception.FundingTimeIsPassedException;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Builder
@Getter
@AllArgsConstructor
public class Funding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_id")
    private Integer id;

    private Integer peopleCount;

    // 이벤트 상태
    @Enumerated(EnumType.STRING)
    private FundingStatus status = CLOSED; // 펀딩이 열리지 않은 영화

    private LocalDateTime startAt;

    /** 펀딩 가능 시간이 아직 안지났는지. */
    public void validFundingTime(Funding funding) {
        funding.validateFundingTime();
    }

    public void validateFundingTime() {
        if (!isTimeBeforeStartAt()) throw FundingTimeIsPassedException.EXCEPTION;
    }

    public void validateNotOpenStatus() {
        if (status != OPEN) throw FundingNotOpenException.EXCEPTION;
    }

    public boolean isTimeBeforeStartAt() {
        return LocalDateTime.now().isBefore(getStartAt());
    }

    public LocalDateTime getStartAt() {
        if (this.startAt == null) return null;
        return this.getStartAt();
    }

    public void validEnoughQuantity(Integer peopleCount) {
        if (this.peopleCount < peopleCount) {
            throw FundingQuantityLackException.EXCEPTION;
        }
    }
}
