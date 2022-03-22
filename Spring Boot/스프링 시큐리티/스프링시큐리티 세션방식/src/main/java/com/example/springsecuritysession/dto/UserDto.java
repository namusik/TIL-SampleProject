package com.example.springsecuritysession.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String nickname;
    private String password;
    private boolean admin = false;
    private String adminToken = "";
}

