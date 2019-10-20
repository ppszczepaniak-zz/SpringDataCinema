package com.example.SpringDataCinema.controller;

import com.example.SpringDataCinema.domain.EMovieCategory;
import com.example.SpringDataCinema.domain.Movie;
import com.example.SpringDataCinema.service.MovieService;
import com.example.SpringDataCinema.utils.IterableUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/getAll") //http://localhost:8080/movie/getAll or use SWAGGER :)
    @ResponseStatus(HttpStatus.OK)
    public Page<Movie> getMovies() {
        return movieService.getAllMovies(Pageable.unpaged());
    }

    @PostMapping("/add") //http://localhost:8080/movie/add
    @ResponseStatus(HttpStatus.CREATED) //201
    public Long addMovie(@RequestBody Movie movie, @RequestHeader String posterFilePath) {
        return movieService.createMovie(movie.getTitle(), movie.getCategory(), movie.getLength(), movie.getDescription(), movie.getRequiredAge(), posterFilePath);
    }

    @GetMapping("/get") //http://localhost:8080/movie/get
    @ResponseStatus(HttpStatus.OK)
    public Movie getMovie(@RequestParam("movieId") long movieId) {  //could be @Param (different Annotation from data.repository.query
        return movieService.getMovie(movieId).get();
    }

    @DeleteMapping("/delete") //http://localhost:8080/movie/delete
    @ResponseStatus(HttpStatus.OK)
    public void deleteMovie(@RequestParam("movieId") long movieId) {
        movieService.removeMovie(movieId);
    }

    @GetMapping("/getInCategory")
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> getMovieByCategory(@RequestParam("category") EMovieCategory category) {
        return IterableUtils.iterableToList(movieService.getMoviesInCategory(category, Pageable.unpaged()));
    }

    @GetMapping("/getByPartOfTitle")
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> getMovieByPartOfTitle(@RequestParam("partOfTitle") String partOfTitle) {
        return IterableUtils.iterableToList(movieService.getMoviesByPartOfTitle(partOfTitle, Pageable.unpaged()));
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateMovie(@RequestBody Movie movie, @RequestHeader String posterFilePath) {
        movieService.updateMovie(movie.getId(), movie.getTitle(), movie.getCategory(), movie.getLength(), movie.getDescription(), movie.getRequiredAge(), posterFilePath);
    }

}


