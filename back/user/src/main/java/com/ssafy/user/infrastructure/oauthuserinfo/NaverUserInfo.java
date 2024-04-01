package com.ssafy.user.infrastructure.oauthuserinfo;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class NaverUserInfo implements OauthUserInfo {

    @JsonProperty("response")
    private Response response;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Response {
        private String id;
        private String birthyear;
        private String age;
        private String nickname;
        private String profile_image;
    }

    @Override
    public String getSocialLoginId() {
        return response.id;
    }

    @Override
    public String getProfileImage() {
        return response.profile_image;
    }

    @Override
    public String getNickname() {
        return response.nickname;
    }
}
