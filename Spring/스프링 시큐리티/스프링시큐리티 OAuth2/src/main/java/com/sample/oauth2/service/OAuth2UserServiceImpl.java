package com.sample.oauth2.service;

import com.sample.oauth2.dto.OAuthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuth2UserServiceImpl implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //DefaultOAuth2User 서비스를 통해 User 정보를 가져와야 하기 때문에 대리자 생성
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        //어떤 서비스인지 구분하는 코드.  google / naver / kakao
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        //OAuth2 로그인 진행시 키가 되는 필드값. 구글에서만 지원.
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        //미리 만들어준 Dto를 사용해서 받아온 데이터를 담아줌.
        OAuthDto oAuthDto =
                OAuthDto.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        log.info("{}", oAuthDto);

        var memberAttribute = oAuthDto.convertToMap();

        //로그인한 유저 리턴.
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")), memberAttribute, "email");
    }
}

