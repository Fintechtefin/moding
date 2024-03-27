package com.ssafy.user.controller;

import static org.springframework.http.HttpHeaders.SET_COOKIE;
import static org.springframework.http.HttpStatus.CREATED;

import com.ssafy.user.domain.User;
import com.ssafy.user.dto.MemberTokens;
import com.ssafy.user.dto.response.AccessTokenResponse;
import com.ssafy.user.service.LoginService;
import com.ssafy.user.service.MyUserDetailsService;
import com.ssafy.user.util.JwtUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    public static final int COOKIE_AGE_SECONDS = 604800;

    private final LoginService loginService;
    private final JwtUtil jwtUtil;
    private final MyUserDetailsService myUserDetailsService;

    @GetMapping("/login/{provider}")
    public ResponseEntity<AccessTokenResponse> login(
            HttpServletRequest request,
            @PathVariable final String provider,
            @RequestParam("code") String code,
            final HttpServletResponse response) {
        final User user = loginService.login(provider, code);
        final MemberTokens memberTokens = jwtUtil.createJwtToken(user);
        final ResponseCookie cookie =
                ResponseCookie.from("refresh-token", memberTokens.getRefreshToken())
                        .maxAge(COOKIE_AGE_SECONDS)
                        .sameSite("None")
                        .secure(true)
                        .httpOnly(true)
                        .path("/")
                        .build();
        response.addHeader(SET_COOKIE, cookie.toString());

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(user.getId().toString());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        return ResponseEntity.status(CREATED)
                .body(new AccessTokenResponse(memberTokens.getAccessToken()));
    }

}
