package com.example.cache.service;

import com.example.cache.model.Movie;
import com.example.cache.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    @Cacheable("hash")
    public Movie getMovie(Long id) {
        return movieRepository.findById(id).orElseThrow(
                IllegalArgumentException::new
        );
    }

    @Cacheable("hash")
    public Movie getMovieByTitle(String title) {
        return movieRepository.findByTitle(title).orElseThrow(
                IllegalArgumentException::new
        );
    }

    @Cacheable(value = "hash", key = "#title + '-' + #director")
    public Movie getMovieByTitleAndDirector(String title, String director) {
        return movieRepository.findByDirectorAndTitle(director, title).orElseThrow(
                IllegalArgumentException::new
        );
    }
}
