package com.example.exchangerate.controller;

import com.example.exchangerate.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ExchangeService exchangeService;

    @GetMapping("/api/test")
    public void exchangeRate() {

        exchangeService.getRate();

    }
}
