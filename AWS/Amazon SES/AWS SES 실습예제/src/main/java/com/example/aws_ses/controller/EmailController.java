package com.example.aws_ses.controller;

import com.example.aws_ses.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService sendEmailService;

    //이메일 전송 API
    @PostMapping("user/api/sendEmail")
    public String sendEmail() throws Exception {

        //이메일 받아와서 list에 add
        List<String> receivers = new ArrayList<>();
        receivers.add("받는이메일");
        //제목
        String subject = "SES Test";

        //본문
        String content = "이메일 테스트";

        sendEmailService.send(subject, content, receivers);
        return "true";
    }
}
