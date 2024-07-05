package com.example.datasourcerouting.repository;

import com.example.datasourcerouting.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByDirector(String title);
}
