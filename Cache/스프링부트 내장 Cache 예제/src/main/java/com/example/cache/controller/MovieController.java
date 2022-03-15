package com.example.cache.controller;

import com.example.cache.model.Movie;
import com.example.cache.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("api/movie")
    public void save() {
        Movie movie = new Movie("올드보이");
        movieService.save(movie);
    }
}
