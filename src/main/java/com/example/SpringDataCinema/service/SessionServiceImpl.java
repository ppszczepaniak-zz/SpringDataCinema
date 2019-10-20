package com.example.SpringDataCinema.service;

import com.example.SpringDataCinema.domain.Movie;
import com.example.SpringDataCinema.domain.Room;
import com.example.SpringDataCinema.domain.Session;
import com.example.SpringDataCinema.exception.EntityDoesNotExistException;
import com.example.SpringDataCinema.repository.MovieRepository;
import com.example.SpringDataCinema.repository.RoomRepository;
import com.example.SpringDataCinema.repository.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;
    private MovieRepository movieRepository;
    private RoomRepository roomRepository;

    public SessionServiceImpl(SessionRepository sessionRepository, MovieRepository movieRepository, RoomRepository roomRepository) {
        this.sessionRepository = sessionRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
    }


    @Override
    @Transactional
    public Long createSession(Long movieId, Long roomId, LocalDateTime startTime) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (!movieOptional.isPresent()) {
            throw new EntityDoesNotExistException("Movie, id = " + movieId);
        }

        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (!roomOptional.isPresent()) {
            throw new EntityDoesNotExistException("Room, id = " + roomId);
        }

        Session session = new Session(null, startTime); //will receive id from DB via sequence
        session.setRoom(roomOptional.get());
        session.setMovie(movieOptional.get());

        sessionRepository.save(session); //gives id  to session

        return session.getId();
    }

    @Override
    public Optional<Session> getSession(Long sessionId) {
        return sessionRepository.findById(sessionId);
    }

    @Override
    public Optional<Session> getSessionWithTickets(Long sessionId) {
        return sessionRepository.readById(sessionId); //gives session with ticketList via EntityGraph (cause Fetch Type was LAZY)
        //TODO check if works without @NamedEntityGraph in Tickets or Session.ticket
    }

    @Override
    public List<Session> getSessionsInDate(LocalDate date) {
        return sessionRepository.findAllByStartDate(Date.valueOf(date)); //converts java.utils.date to java.sql.date required by interface of repository
    }

    @Override
    @Transactional
    public void removeSession(Long sessionId) {
        sessionRepository.deleteById(sessionId);
    }
}
