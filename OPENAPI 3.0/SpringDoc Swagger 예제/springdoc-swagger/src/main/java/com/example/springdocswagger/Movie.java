package com.example.springdocswagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Schema(description = "영화 객체")
public class Movie {
    @Schema(description = "영화 제목", nullable = false, example = "쥬라기공원")
    private String title;
    @Schema(description = "영화 감독", nullable = false, example = "스티븐 스필버그")
    private String director;
    @Schema(description = "제작 국가", nullable = false, example = "미국")
    private String country;
    @Schema(description = "개봉연도", nullable = false, example = "1999")
    private int year;
}