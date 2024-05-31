package com.example.cache.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheService {

    private final CacheManager cacheManager;

    public Collection<String> getCacheNames() {
        return cacheManager.getCacheNames();
    }

    public List<Map.Entry<Object, Object>> getCacheList(String cacheName) {
        // list로 반환하는 캐시
        Cache cache = cacheManager.getCache(cacheName);
        if (cache instanceof ConcurrentMapCache concurrentMapCache) {
            ArrayList<Map.Entry<Object, Object>> result = new ArrayList<>(concurrentMapCache.getNativeCache().entrySet());
            log.info("caches list ::{}", result);
            return result;
        }
        return new ArrayList<>();
    }

    public ConcurrentMap<Object, Object> getCacheMap(String cacheName) {
        // map으로 반환하는 캐시
        Cache cache = cacheManager.getCache(cacheName);
        if (cache instanceof ConcurrentMapCache concurrentMapCache) {
            ConcurrentMap<Object, Object> nativeCache = concurrentMapCache.getNativeCache();
            log.info("caches map ::{}", nativeCache);
            return nativeCache;
        }
        return new ConcurrentHashMap<>();
    }

    @CacheEvict(value = "movie", key = "#oldTitle + '-' + #oldDirector")
    public void deleteCustomCache(String oldTitle, String oldDirector) {
        log.info("cache custom delete :: {}, {}", oldTitle, oldDirector);
    }

    // 기본키로 저장된 캐시를 삭제할 때는 cacheManager를 직접 사용해야된다.
    public void deleteDefaultCache(String oldTitle, String oldDirector) {
        log.info("cache default delete :: {}, {}", oldTitle, oldDirector);
        cacheManager.getCache("movie").evict(new SimpleKey(oldTitle, oldDirector));
    }


//    @Scheduled(fixedRateString = "${caching.movieTTL}")
    @CacheEvict(value = "movie", allEntries = true)
    public void deleteMovieCacheTTL() {
        log.info("10초마다 캐시 삭제");
    }
}
