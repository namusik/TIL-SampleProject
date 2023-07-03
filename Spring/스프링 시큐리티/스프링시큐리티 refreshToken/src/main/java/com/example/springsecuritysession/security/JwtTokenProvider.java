package com.example.springsecuritysession.security;

import com.example.springsecuritysession.exception.CustomException;
import com.example.springsecuritysession.exception.ErrorCode;
import com.example.springsecuritysession.model.UserRoleEnum;
import com.example.springsecuritysession.service.RedisService;
import com.example.springsecuritysession.service.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final RedisService redisService;

    @Value("${jwt.token.key}")
    private String secretKey;

    //토큰 유효시간 설정
    private Long tokenValidTime = 60 * 1000L; //1분
    private Long refreshTokenValidTime = 2 * 60 * 10000L; //2분

    //secretkey를 미리 인코딩 해줌.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    //accessToken 생성
    public String createToken(String email, UserRoleEnum role) {

        return createClaims(tokenValidTime, email, role);
    }

    //refreshToken 생성
    public String createRefreshToken(String email, UserRoleEnum role) {

        return createClaims(refreshTokenValidTime, email, role);
    }

    //token 생성 공통부분
    private String createClaims(Long tokenValidTime, String email, UserRoleEnum role) {
        //registered claims
        Date now = new Date();
        Claims claims = Jwts.claims()
                .setSubject("access_token") //토큰제목
                .setIssuedAt(now) //발행시간
                .setExpiration(new Date(now.getTime() + tokenValidTime)); // 토큰 만료기한

        //private claims
        claims.put("email", email); // 정보는 key - value 쌍으로 저장.
        claims.put("role", role);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT") //헤더
                .setClaims(claims) // 페이로드
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 서명. 사용할 암호화 알고리즘과 signature 에 들어갈 secretKey 세팅
                .compact();
    }

    // Request의 Header에서 token 값을 가져옵니다.
    public String resolveToken(HttpServletRequest request) {

        return request.getHeader("JWT");
    }

    // Request의 Header에서 refreshtoken 값을 가져옵니다.
    public String resolveRefreshToken(HttpServletRequest request) {

        return request.getHeader("REFRESH");
    }


    // 토큰의 유효성 + 만료일자 확인  // -> 토큰이 expire되지 않았는지 True/False로 반환해줌.
    public boolean validateToken(String jwtToken, HttpServletRequest request) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody();
            return !claims.getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            request.setAttribute("exception", ErrorCode.TOKEN_EXPIRED);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //JWT 토큰에서 인증정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("email");
    }

    public UserRoleEnum getUserRole(String token) {
        String enumName = (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("role");
        return Enum.valueOf(UserRoleEnum.class, enumName);
    }

    public boolean existRefreshToken(String refreshToken) {
        //refreshToken에서 이메일 추출
        String key = getUserPk(refreshToken);
        //이메일을 가지고 redis에서 저장된 값 검색.
        String tokenInRedis = redisService.getRedisStringValue(key);
        //redis에 저장된 값과 헤더에 있던 refreshToken이 일치하면 true 반환.
        if (refreshToken.equals(tokenInRedis)) {
            return true;
        } else {
            return false;
        }
    }

    public String reissueAccessToken(String refreshToken, HttpServletRequest request) {//access Token 유효기간 끝났다면
            //refreshToken 검증
            boolean validateRefreshToken = this.validateToken(refreshToken, request);
            //refreshToken Redis에서 이메일과 실제 일치하는지 검증.
            boolean isExistRefreshToken = this.existRefreshToken(refreshToken);
        if (validateRefreshToken && isExistRefreshToken) {
            String email = this.getUserPk(refreshToken);
            UserRoleEnum userRole = this.getUserRole(refreshToken);
            String newAccessToken = this.createToken(email, userRole);
            return newAccessToken;
        } else {
            //refreshToken 유효검증 실패했을 때. 다시 로그인하라고 해야됨.
            throw new CustomException(ErrorCode.NO_LOGIN);
        }
    }
}
