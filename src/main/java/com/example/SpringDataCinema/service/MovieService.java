package com.example.SpringDataCinema.service;

import com.example.SpringDataCinema.domain.EMovieCategory;
import com.example.SpringDataCinema.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MovieService {

    Long createMovie(String title, EMovieCategory category, Integer length, String description, Integer requiredAge, String posterFilePath);

    Optional<Movie> getMovie(Long movieId);

    Page<Movie> getMoviesInCategory(EMovieCategory category, Pageable pageable);

    Page<Movie> getMoviesByPartOfTitle(String partOfTitle, Pageable pageable);

    Page<Movie> getAllMovies(Pageable pageable);

    void updateMovie(Long movieId, String title, EMovieCategory category, Integer length, String description, Integer requiredAge, String posterFilePath);

    void removeMovie(Long movieId);
}
