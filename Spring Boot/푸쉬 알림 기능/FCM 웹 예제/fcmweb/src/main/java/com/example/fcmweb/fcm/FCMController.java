package com.example.fcmweb.fcm;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FCMController {

    private FCMService fcmService;

    @PostMapping("pushnoti")
    public String pushNotification(String clientToken) {

        fcmService.sendPush(clientToken);

        return "success";
    }
}
