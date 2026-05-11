package com.example.springsecuritysession.controller;

import com.example.springsecuritysession.dto.LoginUserDto;
import com.example.springsecuritysession.dto.UserDto;
import com.example.springsecuritysession.model.User;
import com.example.springsecuritysession.model.UserRoleEnum;
import com.example.springsecuritysession.security.JwtTokenProvider;
import com.example.springsecuritysession.security.UserDetailsImpl;
import com.example.springsecuritysession.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    //회원가입 페이지 이동
    @GetMapping("/user/signup")
    public String signupForm(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        try {
            User user = userDetails.getUser();
            model.addAttribute("user", user);
        }catch (NullPointerException e){
            return "signup";
        }
        return "signup";
    }

    //회원가입
    @ResponseBody
    @PostMapping("/user/signup")
    public User signUp(@RequestBody UserDto userDto) {
        User user = userService.signup(userDto);

        return user;
    }

    //로그인 페이지 이동
    @GetMapping("/user/login")
    public String loginForm(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        try {
            User user = userDetails.getUser();
            model.addAttribute("user", user);
        }catch (NullPointerException e){
            model.addAttribute("user", "");
            return "login";
        }
        return "login";
    }

    @PostMapping("/user/login")
    @ResponseBody
    public String login(LoginUserDto loginUserDto, HttpServletResponse response) {

        User user = userService.login(loginUserDto);
        String checkEmail = user.getEmail();
        UserRoleEnum role = user.getRole();

        String token = jwtTokenProvider.createToken(checkEmail, role);
        response.setHeader("JWT", token);

        return token;
    }
}



