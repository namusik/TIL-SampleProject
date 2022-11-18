package com.example.fcmweb.fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FCMService {

    public void sendPush(String clientToken) {


        // See documentation on defining a message payload.
        Message message = Message.builder()
                .putData("score", "850")
                .putData("time", "2:45")
                .setToken(clientToken)
                .build();

        // Send a message to the device corresponding to the provided
        // registration token.

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            log.info("response =========== {}", response);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
