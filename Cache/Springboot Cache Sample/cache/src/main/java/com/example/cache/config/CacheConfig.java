package com.example.cache.config;

import com.example.cache.cache.MovieKeyGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;

@Configuration
@EnableCaching
public class CacheConfig {

    @Value("${caching.enabled}")
    public boolean cacheEnabled;

    @Bean
    public CacheManager cacheManager() {
        if(!cacheEnabled) {
            // 캐시 미사용 (false)
            return new NoOpCacheManager();
        }
        return new ConcurrentMapCacheManager("director" , "title", "movie", "movie2");
    }

    @Bean("movieKeyGenerator")
    public MovieKeyGenerator movieKeyGenerator() {
        return new MovieKeyGenerator();
    }


//    @Bean
    public CacheManager cacheManager2() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache("directory"),
                new ConcurrentMapCache("address")
        ));
        return simpleCacheManager;
    }
}
