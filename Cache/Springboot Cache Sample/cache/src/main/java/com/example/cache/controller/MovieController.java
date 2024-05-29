package com.example.cache.controller;

import com.example.cache.dto.MovieSaveDto;
import com.example.cache.model.Movie;
import com.example.cache.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movies2/{title}")
    public String getDirector(@PathVariable String title) {
        return movieService.getDirector(title);
    }

    @GetMapping("/movies/{title}")
    public Movie findByTitle(@PathVariable String title) {
        return movieService.findByTitle(title);
    }

    @GetMapping("/movies/{title}/{director}")
    public Movie findByDirectorAndTitle(@PathVariable String title, @PathVariable String director) {
        return movieService.findByDirectorAndTitle(title, director);
    }

    @GetMapping("/movies2/{title}/{director}")
    public Movie findByDirectorAndTitle2(@PathVariable String title, @PathVariable String director) {
        return movieService.findByDirectorAndTitle2(title, director);
    }

    @PostMapping("/movies")
    public Movie save(@RequestBody MovieSaveDto movieSaveDto) {
        return movieService.save(movieSaveDto);
    }

    @PostMapping("/movies2")
    public Movie save2(@RequestBody MovieSaveDto movieSaveDto) {
        return movieService.save2(movieSaveDto);
    }

    @PutMapping("/movies/{id}")
    public Movie update(@PathVariable Long id, @RequestBody MovieSaveDto movieSaveDto) {
        return movieService.update(id, movieSaveDto);
    }
}
