package com.example.SpringDataCinema.service;

import com.example.SpringDataCinema.domain.Session;
import com.example.SpringDataCinema.domain.Ticket;
import com.example.SpringDataCinema.exception.BusinessException;
import com.example.SpringDataCinema.exception.EntityDoesNotExistException;
import com.example.SpringDataCinema.repository.SessionRepository;
import com.example.SpringDataCinema.repository.TicketRepository;
import com.example.SpringDataCinema.utils.IterableUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;
    private SessionRepository sessionRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, SessionRepository sessionRepository) {
        this.ticketRepository = ticketRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    @Transactional
    public Long newTicket(Long sessionId, String seat, BigDecimal price) {
        Optional<Session> sessionOptional = sessionRepository.findById(sessionId);
        if (!sessionOptional.isPresent()) {
            throw new EntityDoesNotExistException("Session, id = " + sessionId);
        }

        Long amountOfTicketsInSession = ticketRepository.countBySession(sessionOptional.get());
        if (amountOfTicketsInSession == sessionOptional.get().getRoom().getCapacity()) { //if all tickets from room sold...
            throw new BusinessException("All tickets have been osld out, sessionId = " + sessionId); //our business exception
        }

        Ticket ticket = new Ticket(null, seat, price);

        //our method in Session which gives ticket sessionId, when it's created via ticket.setSession(this);
        sessionOptional.get().addticket(ticket);

        ticketRepository.save(ticket); //saves to DB, gives ID back to object

        return ticket.getId();
    }

    @Override
    public List<Ticket> getAllTicketsForSession(Long sessionId) {

        Optional<Session> sessionOptional = sessionRepository.findById(sessionId);
        if(!sessionOptional.isPresent()){
            throw new EntityDoesNotExistException("Session, id = " + sessionId);
        }

        Iterable<Ticket> tickets = ticketRepository.findAllBySession(sessionOptional.get()); //returns Iterable (required by crudRepository interface)

        return IterableUtils.iterableToList(tickets); //converts iterable to List via our utils class
    }

    @Override
    @Transactional
    public void cancelTicket(Long ticketId) {
        ticketRepository.deleteById(ticketId);

    }
}
