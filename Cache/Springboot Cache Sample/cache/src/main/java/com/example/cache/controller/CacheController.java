package com.example.cache.controller;

import com.example.cache.model.Movie;
import com.example.cache.service.CacheService;
import com.example.cache.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CacheController {

    private final CacheManager cacheManager;
    private final CacheService cacheService;


    @GetMapping("/caches")
    public Collection<String> getCachenNames() {
        return cacheManager.getCacheNames();
    }

    @GetMapping("/caches/all")
    public Map<Object, Object> getAllCaches() {
        return cacheService.getAllCaches("hash");
    }
}
