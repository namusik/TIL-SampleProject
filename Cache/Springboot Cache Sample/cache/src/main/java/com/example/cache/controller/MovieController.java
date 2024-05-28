package com.example.cache.controller;

import com.example.cache.model.Movie;
import com.example.cache.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movies/{title}")
    public Movie getMovies(@PathVariable String title) {
        return movieService.getMovieByTitle(title);
    }

    @GetMapping("/movies/{title}/{director}")
    public Movie getMovies(@PathVariable String title, @PathVariable String director) {
        return movieService.getMovieByTitleAndDirector(title, director);
    }
}
