package com.example.aws_ses.service;

import com.example.aws_ses.model.User;
import com.example.aws_ses.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void changePw(String email, String password) {
        //DB에서 이메일로 일치하는 회원 정보 가져오기
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NullPointerException("없는 회원입니다."));

        //회원의 비밀번호 수정
        user.updatePw(password);
    }
}
