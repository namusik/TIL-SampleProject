package com.sample.oauth2.controller;

import com.sample.oauth2.exception.CustomException;
import com.sample.oauth2.exception.ErrorCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenExceptionController {
    @GetMapping("/exception/entrypoint")
    public void entryPoint() {
        throw new CustomException(ErrorCode.NO_LOGIN);
    }

    @GetMapping("/exception/access")
    public void denied() {
        throw new CustomException(ErrorCode.NO_ADMIN);
    }
}

