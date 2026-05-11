package com.example.springsecuritysession.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
public class LoginUserDto {
    private String email;
    private String password;
}
