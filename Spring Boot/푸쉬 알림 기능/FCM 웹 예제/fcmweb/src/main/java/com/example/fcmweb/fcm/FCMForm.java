package com.example.fcmweb.fcm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FCMForm {

    private String title;
    private String body;
    private String token;
}
