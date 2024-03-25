package com.ssafy.user.controller;

import static com.ssafy.user.util.AuthenticationUtil.getCurrentUserSocialId;

import com.ssafy.user.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final LoginService loginService;

    @DeleteMapping("/logout")
    public ResponseEntity<?> logout() {
        loginService.logout(getCurrentUserSocialId());
        return ResponseEntity.ok().build();
    }
}
