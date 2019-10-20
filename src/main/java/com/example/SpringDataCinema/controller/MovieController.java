package com.example.SpringDataCinema.controller;

import com.example.SpringDataCinema.domain.Movie;
import com.example.SpringDataCinema.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/getAll") //http://localhost:8080/movie/getAll or use SWAGGER :)
    @ResponseStatus(HttpStatus.OK)
    public Page<Movie> getBooks() {
        return movieService.getAllMovies(Pageable.unpaged());
    }

//    @PostMapping("/add") //http://localhost:8080/movie/add
//    @ResponseStatus(HttpStatus.CREATED) //201
//    public Movie addMovie(@RequestBody Movie movie) {
//        return movieService.createMovie()

//    Long createMovie(String title, EMovieCategory category, Integer length, String description, Integer requiredAge, String posterFilePath);
//
//    Optional<Movie> getMovie(Long movieId);
//
//    Page<Movie> getMoviesInCategory(EMovieCategory category, Pageable pageable);
//
//    Page<Movie> getMoviesByPartOfTitle(String partOfTitle, Pageable pageable);
//
//    Page<Movie> getAllMovies(Pageable pageable);
//
//    void updateMovie(Long movieId, String title, EMovieCategory category, Integer length, String description, Integer requiredAge, String posterFilePath);
//
//    void removeMovie(Long movieId);

//
//
//
//        @GetMapping("/get") ////http://localhost:8080/book/get
//        public Book getBook(@RequestParam("bookId") long bookId) {  //could be @Param (different Annotation from data.repository.query
//            return bookService.get(bookId);
//        }

}


