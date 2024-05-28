package com.example.cache.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Slf4j
@Component
public class CustomCacheManagerCustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

    @Override
    public void customize(ConcurrentMapCacheManager cacheManager) {
        cacheManager.setCacheNames(Arrays.asList("cache3", "cache4"));
        log.info("CustomCacheManagerCustomizer is called.");
    }
}
