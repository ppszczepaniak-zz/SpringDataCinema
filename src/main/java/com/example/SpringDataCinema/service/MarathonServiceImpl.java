package com.example.SpringDataCinema.service;

import com.example.SpringDataCinema.domain.Marathon;
import com.example.SpringDataCinema.domain.Movie;
import com.example.SpringDataCinema.exception.EntityDoesNotExistException;
import com.example.SpringDataCinema.repository.MarathonRepository;
import com.example.SpringDataCinema.repository.MovieRepository;
import com.example.SpringDataCinema.utils.IterableUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MarathonServiceImpl implements MarathonService {

    private MarathonRepository marathonRepository;
    private MovieRepository movieRepository;

    public MarathonServiceImpl(MarathonRepository marathonRepository, MovieRepository movieRepository) {
        this.marathonRepository = marathonRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    @Transactional
    public Long createMarathon(String name, LocalDateTime startTime, List<Long> movieIds) {
        List<Movie> movies = new ArrayList<>();
        for (Long movieId : movieIds) {
            Optional<Movie> movieOptional = movieRepository.findById(movieId);
            //not too good in terms of performance (database query in for each loop); could write my own query for this
            if (!movieOptional.isPresent()) {
                throw new EntityDoesNotExistException("Movie, id = " + movieId);
            }
            movies.add(movieOptional.get()); //adds to our internal list
        }

        Marathon marathon = new Marathon(null, name, startTime);
        marathon.setMovies(movies);
        for (Movie movie : movies) {
            movie.getMarathons().add(marathon); //adds info to movies (they know about their marathons)
        }

        marathonRepository.save(marathon);
        return marathon.getId();

    }

    @Override
    public Optional<Marathon> getMarathon(Long marathonId) {
        return marathonRepository.findById(marathonId);
    }

    @Override
    public List<Marathon> getAllMarathons() {
        return IterableUtils.iterableToList(marathonRepository.findAll());
    }

    @Override
    @Transactional
    public void removeMarathon(Long marathonId) {
        marathonRepository.deleteById(marathonId);
    }
}
