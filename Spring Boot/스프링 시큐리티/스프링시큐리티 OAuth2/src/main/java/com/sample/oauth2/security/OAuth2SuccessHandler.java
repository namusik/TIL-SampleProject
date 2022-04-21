package com.sample.oauth2.security;

import com.sample.oauth2.dto.LoginUserDto;
import com.sample.oauth2.dto.UserDto;
import com.sample.oauth2.exception.CustomException;
import com.sample.oauth2.exception.ErrorCode;
import com.sample.oauth2.model.User;
import com.sample.oauth2.model.UserRoleEnum;
import com.sample.oauth2.repository.UserRepository;
import com.sample.oauth2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        System.out.println("oAuth2User = " + oAuth2User);

        //최초 로그인이면 회원가입 처리.
        String email = (String) oAuth2User.getAttributes().get("email");
        String nickname = (String) oAuth2User.getAttributes().get("name");
        String password = UUID.randomUUID().toString();
        UserRoleEnum role = UserRoleEnum.ROLE_MEMBER;

        User user = userRepository.findByEmail(email)
                .orElse(null);

        if (user == null) {
            user = new User(email, nickname, password, role);
            System.out.println("user = " + user);
            userRepository.save(user);
        }

        //강제 로그인 과정 실행
        String token = jwtTokenProvider.createToken(email, role);
        response.setHeader("JWT", token);
    }

}
