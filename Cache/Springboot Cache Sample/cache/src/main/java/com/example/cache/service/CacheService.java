package com.example.cache.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CacheService {

    private final CacheManager cacheManager;

    public Map<Object, Object> getAllCaches(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if(cache instanceof ConcurrentMapCache concurrentMapCache) {
            return new HashMap<>(concurrentMapCache.getNativeCache());
        }
        return new HashMap<>();
    }


}
