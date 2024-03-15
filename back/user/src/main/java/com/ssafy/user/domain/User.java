package com.ssafy.user.domain;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "social_id", unique = true, length = 100)
    private String socialId;

    @Column(length = 20)
    private String name;

    @Column(name = "nick_name", length = 15)
    private String nickName;

    @Column(length = 30)
    private String age;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Platform platform;

    @Column(name = "is_sign_in")
    private boolean isSignIn;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private Role role;

    @Column(name = "image_url", length = 100)
    private String imageUrl;

    // 회원 주문 리스트

    // 펀딩 대기 리스트

    // 펀딩 추진 참여

    // 티켓
}
