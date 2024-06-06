package com.ssafy.user.service;

import com.ssafy.user.domain.SecurityUser;
import com.ssafy.user.domain.User;
import com.ssafy.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findBySocialId(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " : 사용자 존재하지 않음");
        }

        return new SecurityUser(user.get());
    }
}
