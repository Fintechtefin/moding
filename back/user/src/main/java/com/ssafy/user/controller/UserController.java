package com.ssafy.user.controller;

import static com.ssafy.user.util.AuthenticationUtil.getCurrentUserSocialId;

import com.ssafy.user.service.LoginService;
import com.ssafy.user.service.UserService;
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
    private final UserService userService;

    @Operation(description = "로그인 후 사용자 정보를 반환합니다.")
    @GetMapping
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String accessToken) {
        return ResponseEntity.ok().body(userService.getUserInfo(accessToken));
    }

    @Operation(description = "로그아웃")
    @DeleteMapping("/logout")
    public ResponseEntity<?> logout() {
        loginService.logout(getCurrentUserSocialId());
        return ResponseEntity.ok().build();
    }
}
