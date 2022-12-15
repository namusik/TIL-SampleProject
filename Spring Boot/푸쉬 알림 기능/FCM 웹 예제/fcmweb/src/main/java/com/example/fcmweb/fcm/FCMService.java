package com.example.fcmweb.fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FCMService {

    public void sendPush(FCMForm fcmForm) {


        // See documentation on defining a message payload.
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(fcmForm.getTitle())
                        .setBody(fcmForm.getBody()).build())
                .setToken(fcmForm.getToken())
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
