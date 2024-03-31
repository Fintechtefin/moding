package com.ssafy.user.infrastructure.oauthuserinfo;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
public class KakaoUserInfo implements OauthUserInfo {

    @JsonProperty("id")
    private String socialLoginId;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Override
    public String getSocialLoginId() {
        return socialLoginId;
    }

    @Override
    public int getBirthYear() {
        return 0;
    }

    @Override
    public String getProfileImage() {
        return kakaoAccount.kakaoProfile.image;
    }

    @Override
    public String getNickname() {
        return kakaoAccount.kakaoProfile.nickname;
    }

    @NoArgsConstructor(access = PRIVATE)
    private static class KakaoAccount {

        @JsonProperty("profile")
        private KakaoProfile kakaoProfile;
    }

    @NoArgsConstructor(access = PRIVATE)
    private static class KakaoProfile {

        @JsonProperty("nickname")
        private String nickname;

        @JsonProperty("profile_image_url")
        private String image;
    }
}
