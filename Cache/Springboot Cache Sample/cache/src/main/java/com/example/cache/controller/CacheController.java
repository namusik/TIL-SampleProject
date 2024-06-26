package com.example.cache.controller;

import com.example.cache.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequiredArgsConstructor
public class CacheController {

    private final CacheService cacheService;

    @GetMapping("/caches/names")
    public Collection<String> getCacheNames() {
        return cacheService.getCacheNames();
    }

    @GetMapping("/caches/list/{name}")
    public List<Map.Entry<Object, Object>> getCacheDataList(@PathVariable String name) {
        return cacheService.getCacheList(name);
    }

    @GetMapping("/caches/map/{name}")
    public ConcurrentMap<Object, Object> getCacheDataMap(@PathVariable String name) {
        return cacheService.getCacheMap(name);
    }
}
