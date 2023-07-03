package com.example.springsecuritysession.exception;

import com.example.springsecuritysession.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class TokenExpiredHandler {
    @ExceptionHandler(value = {TokenExpiredException.class})
    public ResponseEntity<Object> handleAdminErrorException(TokenExpiredException exception) {
        log.error("throw customException : {}", exception.getErrorCode());
        ResponseDto restApiException = new ResponseDto(exception.getErrorCode().getHttpStatus().value(), exception.getErrorCode().getDetail(), exception.getAccessToken());
        return new ResponseEntity<>(restApiException, exception.getErrorCode().getHttpStatus());
    }
}
