package com.ssafy.user.domain;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {
    private static final long serialVersionUiD = 1L;

    public SecurityUser(com.ssafy.user.domain.User user) {
        super(user.getId().toString(), "{noop}", AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
}
