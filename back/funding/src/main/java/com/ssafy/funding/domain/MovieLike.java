package com.ssafy.funding.domain;

import javax.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "movie_like")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MovieLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_like_id")
    private Integer id;

    //    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "movie_id")
    private Integer movieId;
}
