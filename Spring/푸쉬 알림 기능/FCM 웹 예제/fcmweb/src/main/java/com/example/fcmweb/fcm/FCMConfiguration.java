package com.example.fcmweb.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FCMConfiguration {

    @Bean
    public FirebaseApp firebaseApp() {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/fcmtest-8ea73-firebase-adminsdk-hncgq-f726f4230f.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            return FirebaseApp.initializeApp(options);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
