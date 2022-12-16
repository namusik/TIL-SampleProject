package com.example.springdocswagger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @GetMapping("/v1/getMovie")
    public Movie getMovieV1() {
        Movie movie = new Movie("헤어질 결심", "박찬욱", "대한민국", 2022);
        return movie;
    }

    @GetMapping("/v2/getMovie")
    public Movie getMovieV2() {
        Movie movie = new Movie("아바타2", "제임스 카메론","미국",2022);
        return movie;
    }
}
