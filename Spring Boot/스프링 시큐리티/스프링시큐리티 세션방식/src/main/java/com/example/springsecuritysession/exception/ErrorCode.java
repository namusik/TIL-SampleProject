package com.example.springsecuritysession.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ADMIN_TOKEN(HttpStatus.BAD_REQUEST, "관리자 암호가 일치하지않습니다");

    private HttpStatus httpStatus;
    private String detail;
}
