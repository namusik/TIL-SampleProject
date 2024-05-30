package com.example.cache.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheService {

    private final CacheManager cacheManager;

    public Collection<String> getCacheNames() {
        return cacheManager.getCacheNames();
    }

    public List<Map.Entry<Object, Object>> getAllCaches(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        log.info("cache :: {}", cache);
        if(cache instanceof ConcurrentMapCache concurrentMapCache) {
            ArrayList<Map.Entry<Object, Object>> result = new ArrayList<>(concurrentMapCache.getNativeCache().entrySet());
            System.out.println("result = " + result);
            return result;
        }
        return new ArrayList<>();
    }

    @CacheEvict(value = "hash", key = "#oldTitle + '-' + #oldDirector")
    public void deleteCustomCache(String oldTitle, String oldDirector) {
        log.info("cache custom delete :: {}, {}", oldTitle, oldDirector);
    }

    @CacheEvict(value = "hash", key = "new org.springframework.cache.interceptor.SimpleKey(#oldTitle, #oldDirector)", beforeInvocation = true)
    public void deleteDefaultCache(String oldTitle, String oldDirector) {
        log.info("cache default delete :: {}, {}", oldTitle, oldDirector);
    }
}
