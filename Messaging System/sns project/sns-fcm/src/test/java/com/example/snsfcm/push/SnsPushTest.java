package com.example.snsfcm.push;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootTest
public class SnsPushTest {
    @Autowired
    private AmazonSNS amazonSNS;
    @Test
    void sendPush() {
        String message = "test form springboot";
        String topicArn = "arn:aws:sns:ap-northeast-2:382240023058:sns-push-standard-topic";

        PublishRequest publishRequest = new PublishRequest()
                .withMessage(message)
                .withSubject("My Push Notification")
                .withTopicArn(topicArn); // 푸시 알림을 보낼 토픽 ARN 설정

        PublishResult publishResult = amazonSNS.publish(publishRequest);

        System.out.println("Message sent: " + publishResult.getMessageId());
    }

    @TestConfiguration
    static class SNSConfiguration {
        private final String awsAccessKey = "";

        private final String awsSecretKey = "";

        @Bean
        @Primary // 실제 SNS 빈을 대체하기 위해 @Primary 어노테이션 사용
        public AmazonSNS amazonSNS() {
            BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
            return AmazonSNSClient.builder()
                    .withRegion(Regions.AP_NORTHEAST_2) // 원하는 리전 선택
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                    .build();
        }
    }
}
