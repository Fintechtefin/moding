package com.ssafy.funding.domain;

import static com.ssafy.funding.domain.FundingStatus.OPEN;

import com.ssafy.funding.exception.FundingNotOpenException;
import com.ssafy.funding.exception.FundingQuantityLackException;
import com.ssafy.funding.exception.FundingTimeIsPassedException;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Funding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_id")
    private Integer id;

    private Integer peopleCount;

    private Integer price;

    /*
    이벤트 상태 필드
    ERD에서 없어서 한번 열렸다가 종료된 펀딩은 지울지 이야기 해봐야 함
     */
    @Enumerated(EnumType.STRING)
    private FundingStatus status;

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

    /*
    아래와 같이 수정할 예정 (Redis 연결 필요)
    (전체 펀딩 모집 인원 - 펀딩에 실제 참여한 인원 수) < 현재 유저가 예매하려는 펀딩 수
     */
    public void validEnoughQuantity(Integer peopleCount) {
        if (this.peopleCount < peopleCount) {
            throw FundingQuantityLackException.EXCEPTION;
        }
    }
}
