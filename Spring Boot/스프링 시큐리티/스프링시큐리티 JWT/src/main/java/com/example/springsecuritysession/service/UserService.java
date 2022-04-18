package com.example.springsecuritysession.service;

import com.example.springsecuritysession.dto.UserDto;
import com.example.springsecuritysession.exception.CustomException;
import com.example.springsecuritysession.exception.ErrorCode;
import com.example.springsecuritysession.model.User;
import com.example.springsecuritysession.model.UserRoleEnum;
import com.example.springsecuritysession.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private static final String ADMIN_PW = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";


    public User signup(UserDto userDto) {
        String email = userDto.getEmail();

        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByEmail(email);
        if (found.isPresent()) {
            throw new CustomException(ErrorCode.SAME_USER);
        }

        String nickname = userDto.getNickname();

        //패스워드 암호화
        String password = passwordEncoder.encode(userDto.getPassword());

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.ROLE_MEMBER;
        //true면 == 관리자이면
        //boolean 타입의 getter는 is를 붙인다
        if (userDto.isAdmin()) {
            if (!userDto.getAdminToken().equals(ADMIN_PW)) {
                throw new CustomException(ErrorCode.ADMIN_TOKEN);
            }
            //role을 admin으로 바꿔준다
            role = UserRoleEnum.ROLE_ADMIN;
        }

        User user = new User(email, nickname, password, role);
        userRepository.save(user);
        return user;
    }
}
