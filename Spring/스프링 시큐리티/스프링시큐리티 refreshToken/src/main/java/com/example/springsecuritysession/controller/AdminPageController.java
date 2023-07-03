package com.example.springsecuritysession.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {

    @GetMapping("/api/admin")
    @Secured("ROLE_ADMIN")
    public String adminPage() {
        return "admin";
    }
}
