package com.example.aws_ses;

import com.example.aws_ses.model.User;
import com.example.aws_ses.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Component;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AwsSesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsSesApplication.class, args);
    }

}

@Component
@RequiredArgsConstructor
class CommandLineRunnerImpl implements CommandLineRunner {

    private final UserRepository userRepository;

    //스프링부트 초기화 종료 후 실행되도록. 회원정보 자동 저장.
    @Override
    public void run(String... args) throws Exception {
        User myUser = new User("이메일", "1234", "treesick");
        userRepository.save(myUser);
    }
}
