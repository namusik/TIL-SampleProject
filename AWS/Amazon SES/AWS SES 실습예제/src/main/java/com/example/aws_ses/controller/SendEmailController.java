package com.example.aws_ses.controller;

import com.example.aws_ses.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SendEmailController {

    private final SendEmailService sendEmailService;

    @PostMapping("api/sendEmail")
    public void sendEmail() {
        List<String> receivers = new ArrayList<>();
        receivers.add("wsnam0418@gmail.com");
        String subject = "SES Test";
        String content = "이메일 테스트 전달됐나요?";

        sendEmailService.send(subject, content, receivers);
    }
}
