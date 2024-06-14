package com.example.multipledatasource.repository;

import com.example.multipledatasource.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
