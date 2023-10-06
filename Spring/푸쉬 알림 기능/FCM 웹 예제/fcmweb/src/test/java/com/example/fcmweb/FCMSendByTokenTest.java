package com.example.fcmweb;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class FCMSendByTokenTest {
    private final String tokenA = "ddyKi9opSi6ituoB96fR8n:APA91bFvmy-GmvUewN7uGH7RKG3mDhRAdiCl7Y-aZCiLEYG7k0qV0BqlBA3lNo8M4mD7tHJOKywCv_wn3o_P20rSO12PVwFNa5QDhV-JdqHFBGJryQqEswGfjorO9dXE9iPrKhnIcAvU";
    private final String tokenB = "eJ-IJGneRw-5h9rOI3jLM5:APA91bEh-qX_26K6_mywMGcJ_iRBxVtusSQcy-kFy1Ee5oshdE86_qEPrTNd0yHlLxzIwYgTh0DIK8WlkLdFwWfnaPGVu5WaM9gBmbxr1zz5L553S9NpN-51ZmbBWawx3eq60_FAThNs";
    private static final Map<String, FirebaseApp> appMap = new HashMap<>();

    @BeforeEach
    void initializeFirebaseApp() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("src/test/resources/megabird-test-9f864-firebase-adminsdk-9rjor-d51c9950a3.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        //여러 앱 초기화를 위해 이름 옵션 추가
        FirebaseApp megabirdApp = FirebaseApp.initializeApp(options, "megabirdApp");

        //static map에 firebaseApp put
        appMap.put("megabirdApp", megabirdApp);
    }

    @Test
    @DisplayName("FCM 특정 기기 1대에 Notification 전송")
    void sendNotificationToSpecificDevice() throws FirebaseMessagingException {
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle("noti 제목")
                        .setBody("noti 내용")
                        .build())
                .setToken(tokenA)
                .build();

        List<Message> messageList = Collections.singletonList(message);

        //map에서 해당 app의 fcm 가져옴
        FirebaseApp firebaseApp = appMap.get("megabirdApp");
        BatchResponse batchResponse = FirebaseMessaging.getInstance(firebaseApp).sendAll(messageList);

        System.out.println("batchResponse.getSuccessCount() = " + batchResponse.getSuccessCount());
        System.out.println("batchResponse.getFailureCount() = " + batchResponse.getFailureCount());


        for (SendResponse sendResponse : batchResponse.getResponses()) {
            if (sendResponse.isSuccessful()) {
                System.out.println("sendResponse.getMessageId() = " + sendResponse.getMessageId());
            } else {
                System.out.println("sendResponse.getMessageId() = " + sendResponse.getMessageId());
                System.out.println("sendResponse.getException() = " + sendResponse.getException() + sendResponse.getException().getMessage());
            }
        }
    }

    @Test
    @DisplayName("FCM 특정 기기 1대에 Data 전송")
    void sendDataToSpecificDevice() throws FirebaseMessagingException {
        Message message = Message.builder()
                .putData("title", "제목")
                .putData("body","메세지 내용")
                .setToken(tokenA)
                .build();

        List<Message> messageList = Collections.singletonList(message);

        FirebaseApp firebaseApp = appMap.get("megabirdApp");
        BatchResponse batchResponse = FirebaseMessaging.getInstance(firebaseApp).sendAll(messageList);

        System.out.println("batchResponse.getSuccessCount() = " + batchResponse.getSuccessCount());
        System.out.println("batchResponse.getFailureCount() = " + batchResponse.getFailureCount());

        for (SendResponse sendResponse : batchResponse.getResponses()) {
            if (sendResponse.isSuccessful()) {
                System.out.println("sendResponse = " + sendResponse);
            } else {
                System.out.println("sendResponse.getException() = " + sendResponse.getException());
            }
        }
    }

    /**
     * 필요한 변수
     * 1. 토큰 list
     * 2. title
     * 3. body
     * 4. AppName
     *
     * @throws FirebaseMessagingException
     */
    @Test
    @DisplayName("FCM 여러 기기에 각각 다른 메시지 전송")
    void sendDifferentMessageToMulitiDevices() throws FirebaseMessagingException {
        //given
        MessageDto messageDtoA = new MessageDto("제목A", "내용B", tokenA);
        MessageDto messageDtoB = new MessageDto("제목B", "내용B", tokenB);
        List<MessageDto> messageDtoList = Arrays.asList(messageDtoA, messageDtoB);

        //when
        List<Message> messageList = new ArrayList<>();
        for (MessageDto messageDto : messageDtoList) {
            messageList.add(createMessage(messageDto.getTitle(), messageDto.getBody(), messageDto.getToken()));
        }

        FirebaseApp firebaseApp = appMap.get("megabirdApp");
        BatchResponse batchResponse = FirebaseMessaging.getInstance(firebaseApp).sendAll(messageList);

        //then
        System.out.println("batchResponse.getSuccessCount() = " + batchResponse.getSuccessCount());
        System.out.println("batchResponse.getFailureCount() = " + batchResponse.getFailureCount());

        for (SendResponse sendResponse : batchResponse.getResponses()) {
            if (sendResponse.isSuccessful()) {
                System.out.println("sendResponse.getMessageId() = " + sendResponse.getMessageId());
            } else {
                System.out.println("sendResponse.getException() = " + sendResponse.getException() + sendResponse.getException().getMessage());
            }
        }
        // 전송 실패한 토큰 뽑아내기
        if (batchResponse.getFailureCount() > 0) { //실패건수가 존재한다면
            List<SendResponse> responseList = batchResponse.getResponses();

            List<Map<String, Object>> failedTokens = new ArrayList<>();

            for (int i = 0; i < responseList.size(); i++) { //responseList 반복문 돌려서
                if (!responseList.get(i).isSuccessful()) {
                    Map<String, Object> map = new HashMap<>();
                    String deviceToken = messageDtoList.get(i).getToken(); //reponseList의 순서와 tokenList의 순서가 일치하게 응답이 돌아와서 가능한 방법
                    FirebaseMessagingException exception = responseList.get(i).getException(); //실패이유
                    map.put(deviceToken, exception);
                    failedTokens.add(map);
                }
            }
            System.out.println("failedTokens : " + failedTokens);
        }
    }
    static class MessageDto{
        String title;
        String body;
        String token;

        public MessageDto(String title, String body, String token) {
            this.title = title;
            this.body = body;
            this.token = token;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    private Message createMessage(String title, String body, String token) {
        Message messageA = Message.builder().setNotification(
                    Notification.builder()
                    .setTitle(title)
                    .setBody(body)
                    .build()
                )
                .setToken(token)
                .build();
        return messageA;
    }

    /**
     * 필요한 변수
     * 1. 토큰 list
     * 2. title
     * 3. body
     * 4. AppName
     *
     * @throws FirebaseMessagingException
     */
    @Test
    @DisplayName("FCM 여러 기기에 하나의 메시지 전송")
    void sendSameMessageToMultiDevices() throws FirebaseMessagingException {
        List<String> tokenList = Arrays.asList(tokenA, tokenB);

        MulticastMessage message = createMulticastMessage("제목", "내용", tokenList);

        FirebaseApp firebaseApp = appMap.get("megabirdApp");
        BatchResponse batchResponse = FirebaseMessaging.getInstance(firebaseApp).sendMulticast(message);

        System.out.println("batchResponse.getSuccessCount() = " + batchResponse.getSuccessCount());
        System.out.println("batchResponse.getFailureCount() = " + batchResponse.getFailureCount());

        for (SendResponse sendResponse : batchResponse.getResponses()) {
            if (sendResponse.isSuccessful()) {
                System.out.println("sendResponse.getMessageId() = " + sendResponse.getMessageId());
            } else {
                System.out.println("sendResponse.getMessageId() = " + sendResponse.getMessageId());
                System.out.println("sendResponse.getException() = " + sendResponse.getException() + sendResponse.getException().getMessage());
            }
        }

        // 전송 실패한 토큰 뽑아내기
        if (batchResponse.getFailureCount() > 0) { //실패건수가 존재한다면
            List<SendResponse> responseList = batchResponse.getResponses();

            List<Map<String, Object>> failedTokens = new ArrayList<>();

            for (int i = 0; i < responseList.size(); i++) { //responseList 반복문 돌려서
                if (!responseList.get(i).isSuccessful()) {
                    Map<String, Object> map = new HashMap<>();
                    String deviceToken = tokenList.get(i); //reponseList의 순서와 tokenList의 순서가 일치하게 응답이 돌아와서 가능한 방법
                    FirebaseMessagingException exception = responseList.get(i).getException(); //실패이유
                    map.put(deviceToken, exception);
                    failedTokens.add(map);
                }
            }
            System.out.println("failedTokens : " + failedTokens);
        }
    }

    private static MulticastMessage createMulticastMessage(String title, String body, List<String> tokenList) {
        MulticastMessage message = MulticastMessage.builder()
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .addAllTokens(tokenList)
                .build();
        return message;
    }
}
