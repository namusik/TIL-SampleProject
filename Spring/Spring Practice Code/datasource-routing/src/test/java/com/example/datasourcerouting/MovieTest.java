package com.example.datasourcerouting;

import com.example.datasourcerouting.domain.Movie;
import com.example.datasourcerouting.repository.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MovieTest {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    @DisplayName("읽기 전용 DB")
    @Transactional(readOnly = true)
    void readReplication() {

        Movie movie = movieRepository.findByDirector("봉준호");

        System.out.println("movie = " + movie);

        assertThat(movie.getTitle()).isEqualTo("살인의 추억2");
    }

    @Test
    @DisplayName("쓰기 전용 DB")
    void writeReplication() {
        Movie movie = new Movie("김지운", "조용한 가족");

        Movie saved = movieRepository.save(movie);

        System.out.println("saved = " + saved);
    }
}
