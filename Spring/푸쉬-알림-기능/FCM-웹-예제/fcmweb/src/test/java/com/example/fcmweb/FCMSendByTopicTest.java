package com.example.fcmweb;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.TopicManagementResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FCMSendByTopicTest {
    private final String tokenA = "ddyKi9opSi6ituoB96fR8n:APA91bFvmy-GmvUewN7uGH7RKG3mDhRAdiCl7Y-aZCiLEYG7k0qV0BqlBA3lNo8M4mD7tHJOKywCv_wn3o_P20rSO12PVwFNa5QDhV-JdqHFBGJryQqEswGfjorO9dXE9iPrKhnIcAvU";
    private final String tokenB = "eJ-IJGneRw-5h9rOI3jLM5:APA91bEh-qX_26K6_mywMGcJ_iRBxVtusSQcy-kFy1Ee5oshdE86_qEPrTNd0yHlLxzIwYgTh0DIK8WlkLdFwWfnaPGVu5WaM9gBmbxr1zz5L553S9NpN-51ZmbBWawx3eq60_FAThNs";
    private static Map<String, List<String>> topicMap = new HashMap<>();
    @BeforeEach
    void initializeFirebaseApp() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("src/test/resources/megabird-test-9f864-firebase-adminsdk-9rjor-d51c9950a3.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }


    @Test
    @DisplayName("새로운 FCM Topic 생성")
    void addNewTopic()  {
        List<String> tokenList = Arrays.asList(tokenA);

        String topic = "/topics/event";

        TopicManagementResponse topicManagementResponse ;
        try {
            topicManagementResponse = FirebaseMessaging.getInstance().subscribeToTopic(tokenList, topic);
            System.out.println("put map");
            topicMap.put(topic, tokenList);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("topicManagementResponse = " + topicManagementResponse);
    }

    @Test
    @DisplayName("토픽 리스트와 구독 토큰 가져오기")
    void getTopicList() {

        topicMap.forEach((key, value)-> System.out.println("key = " + key + ": value = " + value) );
    }
}
