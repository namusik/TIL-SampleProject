package com.example.springsecuritysession.controller;

import com.example.springsecuritysession.dto.LoginUserDto;
import com.example.springsecuritysession.dto.UserDto;
import com.example.springsecuritysession.model.User;
import com.example.springsecuritysession.model.UserRoleEnum;
import com.example.springsecuritysession.security.JwtTokenProvider;
import com.example.springsecuritysession.security.UserDetailsImpl;
import com.example.springsecuritysession.service.RedisService;
import com.example.springsecuritysession.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

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
        String email = user.getEmail();
        UserRoleEnum role = user.getRole();

        //토큰 생성
        String token = jwtTokenProvider.createToken(email, role);
        String refreshToken = jwtTokenProvider.createRefreshToken(email, role);

        //refreshToken을 Redis에 저장해주기
        redisService.setRedisStringValue(email, refreshToken);

        response.setHeader("JWT", token);
        response.setHeader("REFRESH", refreshToken);

        return token + "       /////       " + refreshToken;
    }

    @PostMapping("/user/reissue")
    @ResponseBody
    public String reissue(HttpServletResponse response, HttpServletRequest request, @RequestHeader("REFRESH") String refreshToken) {
        String newAccessToken = jwtTokenProvider.reissueAccessToken(refreshToken, request);

        response.setHeader("JWT", newAccessToken);
        return newAccessToken;
    }

//    @PostMapping("/user/logout")
//    @ResponseBody
//    public String logout() {
//        //redis에 저장되어있는 refreshToken 삭제
//    }
}



