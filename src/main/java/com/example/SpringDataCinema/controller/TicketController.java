package com.example.SpringDataCinema.controller;

import com.example.SpringDataCinema.domain.Ticket;
import com.example.SpringDataCinema.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Ticket> getMovies(@RequestParam("sessionId") Long sessionId) {
        return ticketService.getAllTicketsForSession(sessionId);

        //TODO You will encounter error here: JSON will loop Ticket->sessoi->ticketlist->session etc.
        //use @JsonIgnore at correct property such as:
        //@JsonIgnore
        //private int forgetThisField;

    }


    //TODO later:
    //Long newTicket(Long sessionId, String seat, BigDecimal price);
    //void cancelTicket(Long ticketId);


}
