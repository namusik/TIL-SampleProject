package com.sample.oauth2.service;

import com.sample.oauth2.exception.CustomException;
import com.sample.oauth2.exception.ErrorCode;
import com.sample.oauth2.model.User;
import com.sample.oauth2.repository.UserRepository;
import com.sample.oauth2.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    //로그인할 때 들어온 username으로 DB에서 정보 찾기
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.NO_USER));

        //UserDetailsImpl에서 정의한 생성자
        return new UserDetailsImpl(user);
    }
}
