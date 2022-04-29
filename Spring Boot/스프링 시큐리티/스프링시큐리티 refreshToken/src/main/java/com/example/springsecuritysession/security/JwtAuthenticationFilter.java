package com.example.springsecuritysession.security;

import com.example.springsecuritysession.exception.CustomException;
import com.example.springsecuritysession.exception.ErrorCode;
import com.example.springsecuritysession.exception.TokenExpiredException;
import com.example.springsecuritysession.model.UserRoleEnum;
import com.example.springsecuritysession.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더에서 JWT 를 받아옵니다.
        String accessToken = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        String refreshToken = jwtTokenProvider.resolveRefreshToken((HttpServletRequest) request);

        // accessToken이 있는지
        if (accessToken != null) {
            //accessToken이 유효한지
            if (jwtTokenProvider.validateToken(accessToken)) {
                // 토큰 인증과정을 거친 결과를 authentication이라는 이름으로 저장해줌.
                Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
                // SecurityContext 에 Authentication 객체를 저장합니다.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else if (refreshToken != null && !jwtTokenProvider.validateToken(accessToken)) { //access Token 유효기간 끝났다면
                //refreshToken 검증
                boolean validateRefreshToken = jwtTokenProvider.validateToken(refreshToken);
                //refreshToken Redis에서 이메일과 실제 일치하는지 검증.
                boolean isExistRefreshToken = jwtTokenProvider.existRefreshToken(refreshToken);
                if (validateRefreshToken && isExistRefreshToken) {
                    String email = jwtTokenProvider.getUserPk(accessToken);
                    UserRoleEnum userRole = jwtTokenProvider.getUserRole(accessToken);
                    String newAccessToken = jwtTokenProvider.createToken(email, userRole);
                    throw new TokenExpiredException(ErrorCode.TOKEN_EXPIRED, newAccessToken);
                }
            }
        }
        //UsernamePasswordAuthenticationFilter로 이동
        chain.doFilter(request, response);
    }
}