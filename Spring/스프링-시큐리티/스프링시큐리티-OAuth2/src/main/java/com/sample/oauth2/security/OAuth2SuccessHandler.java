package com.sample.oauth2.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        System.out.println("oAuth2User = " + oAuth2User);

        String email = (String) oAuth2User.getAttributes().get("email");
        System.out.println("email = " + email);
        String nickname = (String) oAuth2User.getAttributes().get("name");
        System.out.println("nickname = " + nickname);

        //한글 닉네임인 경우 인코딩
        nickname = URLEncoder.encode(nickname);
        System.out.println("nickname = " + nickname);

        //패스워드 입력하도록 리다이렉트
        response.sendRedirect("/user/oauth/password/"+email+"/"+nickname);
    }
}
