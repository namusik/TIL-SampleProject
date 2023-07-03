package com.example.springsecuritysession.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String email;
    private String nickname;
    private String password;
    private boolean admin;
    private String adminToken;

    @Override
    public String toString() {
        return "UserDto{" +
                "email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                ", adminToken='" + adminToken + '\'' +
                '}';
    }
}

