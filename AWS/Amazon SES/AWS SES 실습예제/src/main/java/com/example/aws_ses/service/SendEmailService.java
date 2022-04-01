package com.example.aws_ses.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.example.aws_ses.dto.EmailSenderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SendEmailService {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    //이메일 전송하기
    public void send(String subject, String content, List<String> receivers) {

        //이메일 정보를 담은 객체 생성
        EmailSenderDto emailSenderDto = new EmailSenderDto(receivers, subject, content);

        //EmailSenderDto를 SendEmailRequest형태로 바꿔준후 이메일 전송.
        SendEmailResult sendEmailResult = amazonSimpleEmailService.sendEmail(emailSenderDto.toSendRequestDto());

        System.out.println(sendEmailResult.getSdkResponseMetadata().toString());
    }
}
