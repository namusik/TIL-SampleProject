package com.example.springdocswagger;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Movie {
    private String title;
    private String director;
    private String country;
    private int year;
}
