package com.ssafy.user.service;

import com.ssafy.user.repository.UserRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final LoginService loginService;
    private final UserRepository userRepository;

    public List<UserRepository.AfterMoodingResponseInterface> getMyFundingResult(
            String accessToken) {
        int userId = loginService.getUsername(accessToken);
        return userRepository.getMyFundingResult(userId);
    }
}
