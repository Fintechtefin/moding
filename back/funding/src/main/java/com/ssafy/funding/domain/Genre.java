package com.ssafy.funding.domain;

import lombok.*;

import javax.persistence.*;

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
