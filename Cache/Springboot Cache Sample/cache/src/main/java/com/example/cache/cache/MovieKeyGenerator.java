package com.example.cache.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.StringJoiner;

@Slf4j
@Component("moveKeyGenerator")
public class MovieKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringJoiner key = new StringJoiner("-");

        for (Object param : params) {
            key.add(param.toString());
        }

        log.info("key: {}", key);

        return key.toString();
    }
}
