package com.example.springsecuritysession.security;

import com.example.springsecuritysession.exception.ErrorCode;
import com.example.springsecuritysession.exception.TokenExpiredException;
import com.example.springsecuritysession.model.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 헤더에서 JWT 를 받아옵니다.
        String accessToken = jwtTokenProvider.resolveToken(request);
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);

        // accessToken이 있는지
        if (accessToken != null && jwtTokenProvider.validateToken(accessToken, request)) {
            // 토큰 인증과정을 거친 결과를 authentication이라는 이름으로 저장해줌.
            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
            // SecurityContext 에 Authentication 객체를 저장합니다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        //UsernamePasswordAuthenticationFilter로 이동
        chain.doFilter(request, response);
    }
}