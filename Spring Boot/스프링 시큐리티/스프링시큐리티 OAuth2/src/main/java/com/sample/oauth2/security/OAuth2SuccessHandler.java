package com.sample.oauth2.security;

import com.sample.oauth2.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        System.out.println("oAuth2User = " + oAuth2User);
//        UserDto userDto = userRequestMapper.toDto(oAuth2User);
//
//        // 최초 로그인이라면 회원가입 처리를 한다.
//
//        Token token = tokenService.generateToken(userDto.getEmail(), "USER");
//        log.info("{}", token);
//
//        writeTokenResponse(response, token);
    }
}
