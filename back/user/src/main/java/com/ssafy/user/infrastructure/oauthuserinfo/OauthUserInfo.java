package com.ssafy.user.infrastructure.oauthuserinfo;

public interface OauthUserInfo {
    String getSocialLoginId();

    int getBirthYear();

    String getProfileImage();

    String getNickname();
}
