package com.sample.oauth2.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더에서 JWT 를 받아옵니다.
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        // 유효한 토큰인지 확인합니다. 유효성검사
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 토큰 인증과정을 거친 결과를 authentication이라는 이름으로 저장해줌.
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            // SecurityContext 에 Authentication 객체를 저장합니다.
            // token이 인증된 상태를 유지하도록 context(맥락)을 유지해줌
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        //UsernamePasswordAuthenticationFilter로 이동
        chain.doFilter(request, response);
    }
}
