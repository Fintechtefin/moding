package com.ssafy.user.repository;

import com.ssafy.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findBySocialId(String socialLoginId);
}
