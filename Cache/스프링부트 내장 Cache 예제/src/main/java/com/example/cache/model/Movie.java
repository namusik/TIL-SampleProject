package com.example.cache.model;

import com.example.cache.dto.MovieRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    public Movie(String title) {
        this.title = title;
    }

    public void update(MovieRequestDto movieRequestDto) {
        this.title = movieRequestDto.getTitle();
    }
}
