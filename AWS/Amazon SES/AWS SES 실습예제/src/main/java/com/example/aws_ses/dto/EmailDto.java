package com.example.aws_ses.dto;

import com.amazonaws.services.simpleemail.model.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailDto {
    //AWS SES에 등록된 도메인명과 일치해야함
    static final String FROM_EMAIL = "SES에 등록된 이메일"; // 보내는 사람

    private List<String> receiver; // 받는 사람
    private String subject; // 제목
    private String content; // 본문

    public EmailDto(List<String> receiver, String subject, String content) {
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
    }

    //SendEmailRequest 객체형태로 맞춰주기
    public SendEmailRequest toSendRequestDto() {
        //목적지 설정
        final Destination destination = new Destination().withToAddresses(this.receiver);

        //제목, 본문 설정
        final Message message = new Message().withSubject(createContent(this.subject))
                .withBody(new Body().withHtml(createContent(this.content)));

        //반환
        return new SendEmailRequest().withSource(FROM_EMAIL).withDestination(destination).withMessage(message);
    }

    //본문 형식 설정
    private Content createContent(final String text) {
        return new Content().withCharset("UTF-8").withData(text);
    }
}
