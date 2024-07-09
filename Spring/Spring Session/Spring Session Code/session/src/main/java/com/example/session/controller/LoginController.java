package com.example.session.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginController {

    HashMap<String, String> sessionMap = new HashMap<>();

    @GetMapping("/login")

    public String login(HttpSession session, @RequestParam String username) {
        sessionMap.put(session.getId(), username);

        return "saved";
    }

    @GetMapping("/myName")
    public String myName(HttpSession httpSession) {

        return sessionMap.get(httpSession.getId());
    }
}
