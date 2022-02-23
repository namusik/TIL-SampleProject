package com.example.filter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {
    @GetMapping("/api/filter")
    public void testFilter() {
        System.out.println("필터 적용된 컨트롤러");
    }

    @GetMapping("/api/nonfilter")
    public void testNotFilter() {
        System.out.println("필터 적용 안된 컨트롤러");
    }
}
