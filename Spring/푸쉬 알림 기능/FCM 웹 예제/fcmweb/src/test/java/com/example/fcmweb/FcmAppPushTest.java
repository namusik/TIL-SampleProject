package com.example.fcmweb;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FcmAppPushTest {
    @BeforeEach
    void initializeFirebaseApp() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("src/test/resources/megabird-test-9f864-firebase-adminsdk-9rjor-d51c9950a3.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }
    @Test
    void sendAppPush() throws FirebaseMessagingException {
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle("제목1")
                        .setBody("메세지 내용1")
                        .build())
                .setToken("ddyKi9opSi6ituoB96fR8n:APA91bFvmy-GmvUewN7uGH7RKG3mDhRAdiCl7Y-aZCiLEYG7k0qV0BqlBA3lNo8M4mD7tHJOKywCv_wn3o_P20rSO12PVwFNa5QDhV-JdqHFBGJryQqEswGfjorO9dXE9iPrKhnIcAvU")
                .build();

        Message message2 = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle("제목1")
                        .setBody("메세지 내용1")
                        .build())
                .setToken("eJ-IJGneRw-5h9rOI3jLM5:APA91bEh-qX_26K6_mywMGcJ_iRBxVtusSQcy-kFy1Ee5oshdE86_qEPrTNd0yHlLxzIwYgTh0DIK8WlkLdFwWfnaPGVu5WaM9gBmbxr1zz5L553S9NpN-51ZmbBWawx3eq60_FAThNs")
                .build();

        List<Message> messageList = Arrays.asList(message, message2);

        BatchResponse batchResponse = FirebaseMessaging.getInstance().sendAll(messageList);

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
}
