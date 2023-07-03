package com.example.springsecuritysession.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TokenExpiredException extends RuntimeException{
    private ErrorCode errorCode;
    private String accessToken;
}
