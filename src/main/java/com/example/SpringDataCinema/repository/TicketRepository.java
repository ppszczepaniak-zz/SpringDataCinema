package com.example.SpringDataCinema.repository;

import com.example.SpringDataCinema.domain.Session;
import com.example.SpringDataCinema.domain.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

    Iterable<Ticket> findAllBySession(Session session);

    Long countBySession(Session session);
    //this will use 'count' in SQL expression
}
