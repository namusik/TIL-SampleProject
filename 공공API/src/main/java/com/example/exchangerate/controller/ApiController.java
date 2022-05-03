package com.example.exchangerate.controller;

import com.example.exchangerate.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ExchangeService exchangeService;

    @GetMapping("/api/test")
    public void exchangeRate() {
        exchangeService.getRate();
    }

    @PostMapping("/api/cal")
    public Double calculate(@RequestParam("cur") Double cur, @RequestParam("unit") String unit) {
        return exchangeService.calculate(cur, unit);
    }
}
