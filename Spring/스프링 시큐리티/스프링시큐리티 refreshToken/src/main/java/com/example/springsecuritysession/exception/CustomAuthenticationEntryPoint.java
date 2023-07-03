package com.example.springsecuritysession.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorCode exception = (ErrorCode) request.getAttribute("exception");
        System.out.println("exception = " + exception);
        if (exception == null) {
            response.sendRedirect("/exception/entrypoint");
        }else if (exception.equals(ErrorCode.TOKEN_EXPIRED)) {
            response.sendRedirect("/exception/tokenexpire");
        }
    }
}
