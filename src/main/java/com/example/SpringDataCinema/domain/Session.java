package com.example.SpringDataCinema.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Entity
//used so @EntityGraph in SessionRepository works properly, more info here: https://www.baeldung.com/spring-data-jpa-named-entity-graphs
@NamedEntityGraph(name = "Session.tickets", attributeNodes = @NamedAttributeNode("tickets"))
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    //orpahan removal: more restrict deleting of entity objects, more here: https://www.objectdb.com/java/jpa/persistence/delete
    private List<Ticket> tickets;  //this name must be in @NamedAttributeNode(!)

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Ticket> getTickets() {

        if (tickets == null) {
            tickets = new ArrayList<>(); //better to return empty list than null
        }
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    //additional method - automatically connects ticket with session

    public void addticket(Ticket ticket) {
        getTickets().add(ticket);
        ticket.setSession(this);
    }

    public Session(Long id, LocalDateTime startTime) {
        this.id = id;
        this.startTime = startTime;
    }

    public Session() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /* these are best implementations of equals() & hashCode() for entities objects
    why? read this: https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session other = (Session) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Session.class.getSimpleName() + " [", "]")
                .add("id=" + id)
                .add("startTime=" + startTime)
                .toString();
    }
}
