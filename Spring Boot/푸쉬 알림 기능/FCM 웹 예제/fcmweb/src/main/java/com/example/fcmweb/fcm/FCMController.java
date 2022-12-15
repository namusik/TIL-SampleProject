package com.example.fcmweb.fcm;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class FCMController {

    private final FCMService fcmService;

    @PostMapping("/fcm")
    @ResponseBody
    public String pushNotification(@RequestBody FCMForm fcmForm) {
        System.out.println("fcmForm = " + fcmForm);
        fcmService.sendPush(fcmForm);

        return "success";
    }
}
