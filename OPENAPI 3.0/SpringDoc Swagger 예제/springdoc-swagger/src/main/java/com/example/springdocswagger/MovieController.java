package com.example.springdocswagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "영화 controller", description = "영화 객체 관련 method들이 모여있다.")
public class MovieController {

    @GetMapping("/v1/list")
    @Operation(summary = "movie list", description = "영화 목록을 불러오는 method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "302", description = "fail", content = @Content)
    })
    public Movie list() {
        Movie movie = new Movie("헤어질 결심", "박찬욱", "대한민국", 2022);
        return movie;
    }

    @GetMapping("/v1/findByTitle")
    @Operation(summary = "movie find by id", description = "영화 제목을 통해 영화 찾는 method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "302", description = "fail", content = @Content)
    })
    public Movie findByTitle(@Parameter(description = "찾고자 하는 영화의 제목") @RequestParam String title) {
        Movie movie = new Movie("헤어질 결심", "박찬욱", "대한민국", 2022);
        return movie;
    }

//    @PutMapping("/v1/bbb")
//    public String bbb() {
//        Movie movie = new Movie("헤어질 결심", "박찬욱", "대한민국", 2022);
//        return movie.getTitle();
//    }
//
//    @PostMapping("/v1/ccc")
//    public String ccc() {
//        Movie movie = new Movie("헤어질 결심", "박찬욱", "대한민국", 2022);
//        return movie.getTitle();
//    }
//
//    @DeleteMapping("/v1/ddd")
//    public String ddd() {
//        Movie movie = new Movie("헤어질 결심", "박찬욱", "대한민국", 2022);
//        return movie.getTitle();
//    }
//
//    @PatchMapping("/v1/eee")
//    public String eee() {
//        Movie movie = new Movie("헤어질 결심", "박찬욱", "대한민국", 2022);
//        return movie.getTitle();
//    }

    @GetMapping("/v2/getMovie")
    public Movie getMovieV2() {
        Movie movie = new Movie("아바타2", "제임스 카메론","미국",2022);
        return movie;
    }
}
