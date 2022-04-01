package com.example.aws_ses;

import com.example.aws_ses.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AwsSesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsSesApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(UserRepository userRepository) throws Exception {
        
    }
}
