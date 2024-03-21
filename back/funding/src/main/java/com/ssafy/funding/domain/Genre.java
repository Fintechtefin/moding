package com.ssafy.funding.domain;

import javax.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "genre")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Integer id;

    @Column(length = 30)
    private String type;
}
