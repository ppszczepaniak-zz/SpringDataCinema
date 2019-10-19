package com.example.SpringDataCinema.repository;

import com.example.SpringDataCinema.domain.EMovieCategory;
import com.example.SpringDataCinema.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

    Page<Movie> findByCategory(EMovieCategory category, Pageable pageable);

    Page<Movie> findByTitleContaining(String partOfTitle, Pageable pageable);
    //Containing uses LIKE in SQL

    //page object contains more than just list - number of pages
    // and number of all elements on the list
}
