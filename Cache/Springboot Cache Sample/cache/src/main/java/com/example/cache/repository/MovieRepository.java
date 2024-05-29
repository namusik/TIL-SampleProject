package com.example.cache.repository;

import com.example.cache.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByTitle(String title);

    Optional<Movie> findByDirectorAndTitle(String director, String title);
}
