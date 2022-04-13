package com.example.springsecuritysession.controller;

import com.example.springsecuritysession.security.UserDetailsImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {

    @GetMapping("/api/admin")
    @Secured("ROLE_ADMIN")
    public String adminPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return "admin";
    }
}
