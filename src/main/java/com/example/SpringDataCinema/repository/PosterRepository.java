package com.example.SpringDataCinema.repository;

import com.example.SpringDataCinema.domain.Movie;
import com.example.SpringDataCinema.domain.Poster;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PosterRepository extends CrudRepository<Poster, Long> {
    Optional<Poster> findByMovie(Movie movie);
}
