package com.ssafy.user.service;

import com.ssafy.user.domain.Platform;
import com.ssafy.user.domain.Role;
import com.ssafy.user.domain.User;
import com.ssafy.user.infrastructure.oauthprovider.OauthProvider;
import com.ssafy.user.infrastructure.oauthprovider.OauthProviders;
import com.ssafy.user.infrastructure.oauthuserinfo.OauthUserInfo;
import com.ssafy.user.repository.UserRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private static final int MAX_TRY_COUNT = 5;
    private static final int FOUR_DIGIT_RANGE = 10000;

    private final OauthProviders oauthProviders;
    private final UserRepository userRepository;

    public User login(final String providerName, final String code) {
        final OauthProvider provider = oauthProviders.mapping(providerName);
        final OauthUserInfo oauthUserInfo = provider.getUserInfo(code);
        final User user =
                findOrCreateMember(
                        providerName,
                        oauthUserInfo.getSocialLoginId(),
                        oauthUserInfo.getBirthYear());
        return user;
    }

    private User findOrCreateMember(
            final String providerName, final String socialLoginId, final int birthYear) {
        return userRepository
                .findBySocialId(socialLoginId)
                .orElseGet(() -> createUser(providerName, socialLoginId, birthYear, "ROLE_USER"));
    }

    private User createUser(
            final String providerName,
            final String socialLoginId,
            final int birthYear,
            final String role) {
        // 랜덤 닉네임 생성 : to do
        return userRepository.save(
                User.builder()
                        .socialId(socialLoginId)
                        .platform(Platform.valueOf(providerName))
                        .age(birthYear)
                        .role(Role.valueOf(role))
                        .build());
    }
}
