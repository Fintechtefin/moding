package com.ssafy.user.service;

import static com.ssafy.user.common.CustomExceptionStatus.NOT_AUTHENTICATED_ACCOUNT;

import com.ssafy.user.domain.User;
import com.ssafy.user.dto.response.UserInfoResponse;
import com.ssafy.user.exception.AuthException;
import com.ssafy.user.repository.UserRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final LoginService loginService;

    public UserInfoResponse getUserInfo(String accessToken) {
        Integer userId = loginService.getUsername(accessToken);

        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new AuthException(NOT_AUTHENTICATED_ACCOUNT));
        return UserInfoResponse.of(user);
    }
}
