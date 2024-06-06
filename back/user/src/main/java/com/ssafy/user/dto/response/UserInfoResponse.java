package com.ssafy.user.dto.response;

import static lombok.AccessLevel.PRIVATE;

import com.ssafy.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class UserInfoResponse {

    private String imageUrl;
    private String nickname;

    public static UserInfoResponse of(User user) {
        return new UserInfoResponse(user.getImageUrl(), user.getNickName());
    }
}
