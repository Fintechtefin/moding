package com.ssafy.reservation.domain;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name = "json", typeClass = JsonType.class)
@Table(name = "reservation")
public class Reservation extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Integer Id;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private String seat;

    private Integer status;

    private Integer userId;

    private Integer fundingId;

    public void changeStatus() {
        this.status = 0;
    }
}
