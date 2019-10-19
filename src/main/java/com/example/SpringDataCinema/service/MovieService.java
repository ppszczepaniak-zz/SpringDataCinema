package com.example.SpringDataCinema.service;

import com.example.SpringDataCinema.domain.EMovieCategory;

public interface MovieService {

    Long createMovie(String title, EMovieCategory category, Integer length, String description, Integer requiredAge, String posterFilePath);

}
