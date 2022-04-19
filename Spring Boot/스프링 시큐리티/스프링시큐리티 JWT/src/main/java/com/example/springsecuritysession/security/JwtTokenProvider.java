package com.example.springsecuritysession.security;

import com.example.springsecuritysession.model.UserRoleEnum;
import com.example.springsecuritysession.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Value("${jwt.token.key}")
    private String secretKey;

    //토큰 유효시간 설정
    private Long tokenValidTime = 240 * 60 * 1000L;

    //secretkey를 미리 인코딩 해줌.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //JWT 토큰 생성
    public String createToken(String email, UserRoleEnum role) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);  // 정보는 key - value 쌍으로 저장.
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // 토큰 만료기한
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과 signature 에 들어갈 secret값 세팅
                .compact();
    }

}
