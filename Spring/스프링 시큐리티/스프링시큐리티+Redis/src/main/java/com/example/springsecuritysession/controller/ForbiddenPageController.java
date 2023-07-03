package com.example.springsecuritysession.controller;

import com.example.springsecuritysession.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForbiddenPageController {

    @GetMapping("/forbidden")
    public String forbiddenPage() {
        return "forbidden";
    }
}
