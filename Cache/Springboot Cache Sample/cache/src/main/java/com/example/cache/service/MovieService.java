package com.example.cache.service;

import com.example.cache.dto.MovieSaveDto;
import com.example.cache.model.Movie;
import com.example.cache.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final CacheService cacheService;

    @Cacheable(value = "director", keyGenerator = "movieKeyGenerator")
    public String getDirector(String title) {
        log.info("DB에서 조회");
        Movie movie = movieRepository.findByTitle(title).orElseThrow(
                IllegalArgumentException::new
        );
        return movie.getDirector();
    }

    @Cacheable("title")
    public Movie findByTitle(String title) {
        log.info("DB에서 조회");
        return movieRepository.findByTitle(title).orElseThrow(
                IllegalArgumentException::new
        );
    }

    @Cacheable(value = {"movie", "movie2"}, key = "#title + '-' + #director")
    public Movie findByDirectorAndTitle(String title, String director) {
        // 캐시를 저장할 때 key를 지정해 주는 경우
        log.info("DB에서 조회");
        return movieRepository.findByDirectorAndTitle(director, title).orElseThrow(
                IllegalArgumentException::new
        );
    }

    @Cacheable("movie")
    public Movie findByDirectorAndTitleDefault(String title, String director) {
        // 캐시를 저장할 때 따로 key를 지정해 주지 않는 경우
        log.info("DB에서 조회");
        return movieRepository.findByDirectorAndTitle(director, title).orElseThrow(
                IllegalArgumentException::new
        );
    }

    public Movie save(MovieSaveDto movieSaveDto) {
        log.info("영화 저장 요청 :: {}", movieSaveDto);
        return movieRepository.save(new Movie(movieSaveDto.getTitle(), movieSaveDto.getDirector()));
    }

    @CachePut(value = "movie", key = "#movieSaveDto.title + '-' + #movieSaveDto.director")
    public Movie update(Long id, MovieSaveDto movieSaveDto) {
        Movie movie = movieRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("movie not found")
        );

        String oldTitle = movie.getTitle();
        String oldDirector = movie.getDirector();

        movie.setDirector(movieSaveDto.getDirector());
        movie.setTitle(movieSaveDto.getTitle());

        Movie updatedMovie = movieRepository.save(movie);

        log.info("movie 업데이트 완료");

        // 수정한 데이터 캐시에서 삭제
        // db 업데이트가 실패할 수 있기 때문에 db 업데이트 이후에 동작하도록
//        cacheService.deleteCustomCache(oldTitle, oldDirector);
        cacheService.deleteDefaultCache(oldTitle, oldDirector);

        return updatedMovie;
    }

    @Cacheable("movie")
    public List<Movie> getMovies() {
        log.info("전체 영화 리스트 조회");
        return movieRepository.findAll();
    }
}
