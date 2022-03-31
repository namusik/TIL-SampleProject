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

    //전송 코드
    public void send(String subject, String content, List<String> receivers) {

        EmailSenderDto emailSenderDto = new EmailSenderDto(receivers, subject, content);

        SendEmailResult sendEmailResult = amazonSimpleEmailService.sendEmail(emailSenderDto.toSendRequestDto());

        System.out.println(sendEmailResult.getSdkResponseMetadata().toString());
    }
}
