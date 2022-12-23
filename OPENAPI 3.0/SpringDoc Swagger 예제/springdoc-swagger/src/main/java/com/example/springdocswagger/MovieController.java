package com.example.springdocswagger;

import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {

    @GetMapping("/v1/getMovie")
    public Movie getMovie() {
        Movie movie = new Movie("헤어질 결심", "박찬욱", "대한민국", 2022);
        return movie;
    }

//    @PutMapping("/v1/bbb")
//    public String bbb() {
//        Movie movie = new Movie("헤어질 결심", "박찬욱", "대한민국", 2022);
//        return movie.getTitle();
//    }
//
//    @PostMapping("/v1/ccc")
//    public String ccc() {
//        Movie movie = new Movie("헤어질 결심", "박찬욱", "대한민국", 2022);
//        return movie.getTitle();
//    }
//
//    @DeleteMapping("/v1/ddd")
//    public String ddd() {
//        Movie movie = new Movie("헤어질 결심", "박찬욱", "대한민국", 2022);
//        return movie.getTitle();
//    }
//
//    @PatchMapping("/v1/eee")
//    public String eee() {
//        Movie movie = new Movie("헤어질 결심", "박찬욱", "대한민국", 2022);
//        return movie.getTitle();
//    }

    @GetMapping("/v2/getMovie")
    public Movie getMovieV2() {
        Movie movie = new Movie("아바타2", "제임스 카메론","미국",2022);
        return movie;
    }
}
