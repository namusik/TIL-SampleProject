package com.example.aws_ses.controller;

import com.example.aws_ses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //사용자가 받은 이메일 클릭했을 떄 이동하는 view
    @GetMapping("user/resetpw/{email}")
    public String goResetPage(@PathVariable("email") String email, Model model) {
        //사용자가 누군지 알기위해 email값을 같이 넘겨준다
        model.addAttribute("email", email);
        return "resetpw";
    }

    //비밀번호 찾기 눌렀을 때
    @GetMapping("/user/enterEmail")
    public String enterEmail(Model model) {
        return "enterEmail";
    }


    @PostMapping("user/resetpw")
    @ResponseBody
    public String modifyPw(@RequestParam("email") String email, @RequestParam("password") String password) {

        System.out.println(email);
        System.out.println(password);

        userService.changePw(email, password);

        return "success";
    }
}
