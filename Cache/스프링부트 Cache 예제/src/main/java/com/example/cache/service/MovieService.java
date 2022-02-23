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
    
    //DB에 데이터 저장
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Cacheable(value = "movie")
    public Movie get(Long id) {
        return movieRepository.findById(id).orElseThrow(
                () -> new NullPointerException("no book"));
    }
}
