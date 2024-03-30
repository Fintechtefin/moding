package com.ssafy.user.controller;

import static com.ssafy.user.util.AuthenticationUtil.getCurrentUserSocialId;

import com.ssafy.user.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
// @CrossOrigin(origins = "*")
public class UserController {

    private final LoginService loginService;

    @Operation(description = "로그아웃")
    @DeleteMapping("/logout")
    public ResponseEntity<?> logout() {
        loginService.logout(getCurrentUserSocialId());
        return ResponseEntity.ok().build();
    }
}
