package com.example.springsecuritysession.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {AdminTokenErrorException.class})
    public String handleAdminErrorException(RuntimeException exception) {
        return exception.getMessage();
    }
}
