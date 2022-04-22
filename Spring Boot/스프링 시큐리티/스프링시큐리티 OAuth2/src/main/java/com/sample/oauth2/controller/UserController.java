package com.sample.oauth2.controller;

import com.sample.oauth2.dto.LoginUserDto;
import com.sample.oauth2.dto.UserDto;
import com.sample.oauth2.model.User;
import com.sample.oauth2.model.UserRoleEnum;
import com.sample.oauth2.security.JwtTokenProvider;
import com.sample.oauth2.security.UserDetailsImpl;
import com.sample.oauth2.service.UserService;
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

    //JWT 로그인 처리
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

    //OAuth로 로그인 시 비밀번호 입력 창으로
    @GetMapping("/user/oauth/password/{email}/{nickname}")
    public String oauth(@PathVariable("email") String email, @PathVariable("nickname") String nickname, Model model) {
        System.out.println("email = " + email);
        System.out.println("nickname = " + nickname);
        model.addAttribute("email", email);
        model.addAttribute("nickname", nickname);
        return "oauthPassword";
    }
}
