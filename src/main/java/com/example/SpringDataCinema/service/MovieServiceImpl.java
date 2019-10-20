package com.example.SpringDataCinema.service;

import com.example.SpringDataCinema.domain.EMovieCategory;
import com.example.SpringDataCinema.domain.Movie;
import com.example.SpringDataCinema.domain.Poster;
import com.example.SpringDataCinema.exception.EntityDoesNotExistException;
import com.example.SpringDataCinema.repository.MovieRepository;
import com.example.SpringDataCinema.repository.PosterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true) //so all transactions in this class CAN NOT updated DB (unless...)
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    private PosterRepository posterRepository;

    public MovieServiceImpl(MovieRepository movieRepository, PosterRepository posterRepository) {
        this.movieRepository = movieRepository;
        this.posterRepository = posterRepository;
    }

    @Override
    @Transactional // ...unless it is overwritten here
    //for each method that updates DB, @Transactional annotation must be declared (it overrided read-only mentioned above)
    public Long createMovie(String title, EMovieCategory category, Integer length, String description, Integer requiredAge, String posterFilePath) {
        Movie movie = new Movie(null, title, category, length, description, requiredAge);
        movieRepository.save(movie); //will give me Id from DB via sequence in PostgreSQL

        if (posterFilePath != null) {
            createPoster(movie, posterFilePath); //adds poster if not present
        }

        return movie.getId(); //gives back Id from DB
    }

    private void createPoster(Movie movie, String posterFilePath) {
        Poster poster = new Poster(null, posterFilePath);
        poster.setMovie(movie);
        posterRepository.save(poster); //
    }

    @Override
    public Optional<Movie> getMovie(Long movieId) {
        return movieRepository.findById(movieId);
    }

    @Override
    public Page<Movie> getMoviesInCategory(EMovieCategory category, Pageable pageable) {
        return movieRepository.findByCategory(category, pageable);
    }

    @Override
    public Page<Movie> getMoviesByPartOfTitle(String partOfTitle, Pageable pageable) {
        return movieRepository.findByTitleContaining(partOfTitle, pageable);
    }

    @Override
    public Page<Movie> getAllMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void updateMovie(Long movieId, String title, EMovieCategory category, Integer length, String description, Integer requiredAge, String posterFilePath) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId); //finds in DB

        if (!movieOptional.isPresent()) {   //if doesn't exists in DB
            throw new EntityDoesNotExistException("Movie, id = " + movieId);
        }

        Movie movie = movieOptional.get(); //returns Movie object from Optional
        movie.setTitle(title);
        movie.setCategory(category);
        movie.setDescritpion(description);
        movie.setLength(length);
        movie.setRequiredAge(requiredAge);

        movieRepository.save(movie); //updates this Entity in DB

        Optional<Poster> posterOptional = posterRepository.findByMovie(movie); //finds poster for this movie

        if (posterOptional.isPresent()) { //if present
            Poster poster = posterOptional.get(); //get it from Optional

            if (posterFilePath != null) { //if FrontEnd wants to update filePath
                poster.setFilePath(posterFilePath); //updates its filePath
                posterRepository.save(poster);
            } else { //if FrontEnd sends null we assume user wants to delete it from DB
                posterRepository.delete(poster);
            }

        } else if (posterFilePath != null) { //if poster not in DB, then add it (if there's a new filePath)
            createPoster(movie, posterFilePath);  //creates and adds to DB
        }

    }

    @Override
    @Transactional
    public void removeMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }
}
