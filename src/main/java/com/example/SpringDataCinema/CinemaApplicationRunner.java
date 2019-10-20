package com.example.SpringDataCinema;

import com.example.SpringDataCinema.domain.*;
import com.example.SpringDataCinema.service.MarathonService;
import com.example.SpringDataCinema.service.MovieService;
import com.example.SpringDataCinema.service.SessionService;
import com.example.SpringDataCinema.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


public class CinemaApplicationRunner implements CommandLineRunner {

    private final static Logger LOG = LoggerFactory.getLogger(CinemaApplicationRunner.class);

    private MarathonService marathonService;
    private MovieService movieService;
    private SessionService sessionService;
    private TicketService ticketService;

    public CinemaApplicationRunner(MarathonService marathonService, MovieService movieService, SessionService sessionService, TicketService ticketService) {
        this.marathonService = marathonService;
        this.movieService = movieService;
        this.sessionService = sessionService;
        this.ticketService = ticketService;
    }

    @Override
    public void run(String... args) throws Exception {
        movieServiceInvocations();
        sessionServiceInvocations();
        ticketServiceInvocations();
        marathonServiceInvocations();
    }

    private void movieServiceInvocations() {

        Page<Movie> allMovies = movieService.getAllMovies(PageRequest.of(1, 3, Sort.by("title")));
        LOG.info("1. AllMovies. TotalElements={}, TotalPages={}", allMovies.getTotalElements(), allMovies.getTotalPages());
        allMovies.get().forEach(movie -> LOG.info(" {}", movie));

        Page<Movie> moviesByPartOfTitle = movieService.getMoviesByPartOfTitle("gry", Pageable.unpaged());
        LOG.info("2. MoviesByPartOfTitle. TotalElements={}, TotalPages={}", moviesByPartOfTitle.getTotalElements(), moviesByPartOfTitle.getTotalPages());
        moviesByPartOfTitle.get().forEach(movie -> LOG.info(" {}", movie));

        Page<Movie> comedyMovies = movieService.getMoviesInCategory(EMovieCategory.COMEDY, Pageable.unpaged());
        LOG.info("3. comedyMovies. TotalElements={}, TotalPages={}", comedyMovies.getTotalElements(), comedyMovies.getTotalPages());
        comedyMovies.get().forEach(movie -> LOG.info(" {}", movie));

        Movie movieWithId7 = movieService.getMovie(7L).get();
        LOG.info("4. movieWithId7 - {}", movieWithId7);
        movieService.updateMovie(7L, movieWithId7.getTitle(), EMovieCategory.DRAMA, 140, movieWithId7.getDescritpion(), 18, "/posterGreenBook.png");

        Movie movieWithId7AfterUpdate = movieService.getMovie(7L).get();
        LOG.info("5. movieWithId7AfterUpdate - {}", movieWithId7AfterUpdate);

        Long newMovieId = movieService.createMovie("Jak wytresować smoka 3", EMovieCategory.FAMILY, 104, "Witamy w krainie wikingów i dzikich smoków...", 7, null);
        Movie newMovie = movieService.getMovie(newMovieId).get();
        LOG.info("6. newlovie - {}", newMovie);

        movieService.removeMovie(5L);

    }

    private void sessionServiceInvocations() {
        List<Session> sessionsIn02052019 = sessionService.getSessionsInDate(LocalDate.of(2019, 5, 2));
        LOG.info("7. sessionsIn02052019. AmountOfSessions={}", sessionsIn02052019.size());
        sessionsIn02052019.forEach(session -> LOG.info(" {}", session));

        Session sessionWithId15 = sessionService.getSession(15L).get();
        LOG.info("8. sessionWithId15 - {}, MovieTitle='{}', Room='{}'", sessionWithId15, sessionWithId15.getMovie().getTitle(), sessionWithId15.getRoom().getName());
        // sessionWithId15.getTickets().forEach(ticket -> LOG.info(" {}", ticket));
        // this won't work, cause this:
        // LAZY - zależności nie są uzupełniane. Zostaną “dociągnięte” gdy będzie do nich odwołanie.
        // Należy tutaj zaznaczyć, że to odwołanie musi być w transakcji (adnotacja @Transactional w serwisie),
        // ponieważ gdy odwołanie będzie wyżej, poza transakcją, to wystąpi wyjątek org.hibernate.LazyInitializationException,
        // który świadczy właśnie o tym że próbujemy dostać się do danych które jeszcze nie zostały uzupełnione, a uzupełnić się ich nie da bo jesteśmy poza transakcją.


        Session sessionWithId15WithTickets = sessionService.getSessionWithTickets(15L).get();
        LOG.info("9. sessionWithId15withTickets - {}, MovieTitle='{}', Room='{}'", sessionWithId15WithTickets, sessionWithId15WithTickets.getMovie().getTitle(), sessionWithId15WithTickets.getRoom().getName());
        sessionWithId15WithTickets.getTickets().forEach(ticket -> LOG.info(" {}", ticket));

        Long newSessionId = sessionService.createSession(11L, 4L, LocalDateTime.of(2019, 5, 3, 10, 30));
        Session newSession = sessionService.getSession(newSessionId).get();
        LOG.info("10. newSession - {}", newSession);

        sessionService.removeSession(16L);
    }

    private void ticketServiceInvocations() {
        List<Ticket> ticketsOnSessionWithId15 = ticketService.getAllTicketsForSession(15L);
        LOG.info("11. ticketsOnSessionWithId15. AmountOfTickets={}", ticketsOnSessionWithId15.size());
        ticketsOnSessionWithId15.forEach(ticket -> LOG.info(" {}", ticket));

        Long newTicketId = ticketService.newTicket(15L, "r2m5", new BigDecimal("22.50"));
        LOG.info("12. newTicketId={}", newTicketId);

        ticketService.cancelTicket(18L);

        List<Ticket> ticketsOnSessionWithId15AfterUpdates = ticketService.getAllTicketsForSession(15L);
        LOG.info("13. ticketsOnSessionWithId15AfterUpdates. AmountOfTickets={}", ticketsOnSessionWithId15AfterUpdates.size());
        ticketsOnSessionWithId15AfterUpdates.forEach(ticket -> LOG.info(" {}", ticket));

    }

    private void marathonServiceInvocations() {
        Long newMarathonId = marathonService.createMarathon("Maraton horrorów, 30.10.2018", LocalDateTime.of(2018, 10, 30, 20, 0), Arrays.asList(6L, 8L, 9L));
        Marathon newMarathon = marathonService.getMarathon(newMarathonId).get();
        LOG.info("14. newMarathon - {}", newMarathon);
        newMarathon.getMovies().forEach(movie -> LOG.info(" {}", movie));

        List<Marathon> marathons = marathonService.getAllMarathons();
        LOG.info("15. marathons. AmountOfMarathons={}", marathons.size());
        marathons.forEach(marathon -> LOG.info(" {}", marathon));

        marathonService.removeMarathon(newMarathonId);
    }

}