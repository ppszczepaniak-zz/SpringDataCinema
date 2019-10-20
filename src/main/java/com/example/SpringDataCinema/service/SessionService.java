package com.example.SpringDataCinema.service;

import com.example.SpringDataCinema.domain.Session;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SessionService {

    Long createSession(Long movieId, Long roomId, LocalDateTime startTime);

    Optional<Session> getSession(Long sessionId);

    Optional<Session> getSessionWtihTickets(Long sessionId);

    List<Session> getSessionsInDate(LocalDateTime date);

    void removeSession(Long sessionId);

}
