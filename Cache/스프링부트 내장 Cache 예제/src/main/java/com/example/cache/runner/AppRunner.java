package com.example.cache.runner;

import com.example.cache.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final MovieService movieService;

    @Override
    public void run(String... args) throws Exception {
        log.info("영화 검색하기");
        log.info("movieId=1 ------- " + movieService.get(1L));
        log.info("movieId=1 ------- " + movieService.get(1L));
        log.info("movieId=1 ------- " + movieService.get(1L));
    }
}
