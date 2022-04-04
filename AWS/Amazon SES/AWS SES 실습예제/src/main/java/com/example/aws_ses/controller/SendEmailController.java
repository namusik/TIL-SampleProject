package com.example.aws_ses.controller;

import com.example.aws_ses.security.Aes;
import com.example.aws_ses.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SendEmailController {

    private final SendEmailService sendEmailService;

    //이메일 전송 API
    @PostMapping("user/api/sendEmail")
    public String sendEmail(@RequestParam("email") String email) throws Exception {

        //이메일 받아와서 list에 add
        List<String> receivers = new ArrayList<>();
        receivers.add(email);
        //제목
        String subject = "SES Test";

        //링크를 보낼 때, email 부분을 문자열로 바꿔줘야함. 노출이 안되도록. Aes의 방식을 사용하니 암호문자에 '/'가 포함되서 인식오류
//        Aes aes = new Aes();
//        String enc = aes.encrypt(email);

        //패스워드 입력할 수 있는 API URI를 본문에 담아서 보내줌.
        String content = "http://localhost:8080/user/resetpw/"+email;

        sendEmailService.send(subject, content, receivers);

        return "true";
    }
}
