package com.sample.oauth2.service;

import com.sample.oauth2.dto.LoginUserDto;
import com.sample.oauth2.dto.UserDto;
import com.sample.oauth2.exception.CustomException;
import com.sample.oauth2.exception.ErrorCode;
import com.sample.oauth2.model.User;
import com.sample.oauth2.model.UserRoleEnum;
import com.sample.oauth2.repository.UserRepository;
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
            throw new CustomException(ErrorCode.SAME_EMAIL);
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

    //로그인
    public User login(LoginUserDto loginUserDto) {
        User user = userRepository.findByEmail(loginUserDto.getEmail()).orElseThrow(
                () -> new CustomException(ErrorCode.NO_USER)
        );
        if (!passwordEncoder.matches(loginUserDto.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.NO_USER);
        }
        return user;
    }
}