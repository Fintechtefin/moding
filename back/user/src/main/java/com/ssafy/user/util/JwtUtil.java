package com.ssafy.user.util;

import com.ssafy.user.domain.User;
import com.ssafy.user.dto.MemberTokens;
import com.ssafy.user.infrastructure.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final RedisUtil redisUtil;
    private final JwtProvider jwtProvider;

    public MemberTokens createJwtToken(User user) {
        MemberTokens memberTokens = jwtProvider.generateLoginToken(user.getSocialId(), "");
        redisUtil.setData(user.getSocialId(), memberTokens.getRefreshToken());
        return memberTokens;
    }
}