package com.example.cache.service;

import com.example.cache.dto.MovieSaveDto;
import com.example.cache.model.Movie;
import com.example.cache.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    @Cacheable("hash")
    public String getDirector(String title) {
        log.info("DB에서 조회");
        Movie movie = movieRepository.findByTitle(title).orElseThrow(
                IllegalArgumentException::new
        );
        return movie.getDirector();
    }

    @Cacheable("hash")
    public Movie findByTitle(String title) {
        log.info("DB에서 조회");
        return movieRepository.findByTitle(title).orElseThrow(
                IllegalArgumentException::new
        );
    }

    @Cacheable(value = {"hash", "string"}, key = "#title + '-' + #director")
    public Movie findByDirectorAndTitle(String title, String director) {
        log.info("DB에서 조회");
        return movieRepository.findByDirectorAndTitle(director, title).orElseThrow(
                IllegalArgumentException::new
        );
    }

    @Cacheable("hash")
    public Movie findByDirectorAndTitle2(String title, String director) {
        log.info("DB에서 조회");
        return movieRepository.findByDirectorAndTitle(director, title).orElseThrow(
                IllegalArgumentException::new
        );
    }

    @CacheEvict("hash")
    public Movie save(MovieSaveDto movieSaveDto) {
        log.info("영화 저장 :: {}", movieSaveDto);
        return movieRepository.save(new Movie(movieSaveDto.getTitle(), movieSaveDto.getDirector()));
    }

    @CacheEvict(value = "hash", allEntries = true)
    public Movie save2(MovieSaveDto movieSaveDto) {
        log.info("영화 저장 :: {}", movieSaveDto);
        return movieRepository.save(new Movie(movieSaveDto.getTitle(), movieSaveDto.getDirector()));
    }

    public Movie update(Long id, MovieSaveDto movieSaveDto) {
        Movie movie = movieRepository.findById(id).orElseThrow(
                IllegalArgumentException::new
        );

        String oldTitle = movie.getTitle();
        String oldDirector = movie.getDirector();

        movie.setDirector(movieSaveDto.getDirector());
        movie.setTitle(movieSaveDto.getTitle());

        Movie updatedMovie = movieRepository.save(movie);

        log.info("movie 업데이트 완료");

        deleteCache(oldTitle, oldDirector);

        return updatedMovie;
    }

    @CacheEvict(value = "hash", key = "new org.springframework.cache.interceptor.SimpleKey(#oldTitle, #oldDirector)")
    public void deleteCache(String oldTitle, String oldDirector) {
        log.info("cache delete :: {}, {}", oldTitle, oldDirector);
    }
}
