package com.ssafy.user.infrastructure.oauthuserinfo;


import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    }

    @Override
    public String getSocialLoginId() {
        return response.id;
    }

    @Override
    public int getBirthYear() {
        int birthYear= Integer.parseInt(response.birthyear);
        int currYear= LocalDate.now().getYear();
        return currYear-birthYear+1;
    }

    @Override
    public String getAge() {
        return response.age;
    }
}
