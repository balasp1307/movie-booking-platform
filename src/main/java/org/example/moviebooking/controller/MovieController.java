package org.example.moviebooking.controller;

import lombok.RequiredArgsConstructor;
import org.example.moviebooking.domain.MovieIndex;
import org.example.moviebooking.service.MovieSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieSearchService movieSearchServiceService;

    @GetMapping("/search")
    public List<MovieIndex> search(@RequestParam String title) {
        return movieSearchServiceService.search(title);
    }
}
