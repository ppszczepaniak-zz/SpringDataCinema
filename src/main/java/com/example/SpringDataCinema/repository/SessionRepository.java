package com.example.SpringDataCinema.repository;

import com.example.SpringDataCinema.domain.Session;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


public interface SessionRepository extends JpaRepository<Session, Long> {

    //we create custom query using JPQL (part of JPA) (WARNING: query is slightly different than SQL query!)
    @Query(value = "SELECT s FROM Session s WHERE function('DATE_TRUNC','day',s.startTime)=?1")
    List<Session> findAllByStartDate(Date date);


    //this fills up tickets list for session (Fetch Type was LAZY, so we fill up missing data here)
    //must add @NamedEntityGraph in entity (Session) class!
    //more info here: https://www.baeldung.com/spring-data-jpa-named-entity-graphs
    @EntityGraph(value = "Session.tickets", type = EntityGraph.EntityGraphType.LOAD)
    //OR JUST USE THIS (no need to use @NamedEntityGraph in Session class):
    //@EntityGraph(attributePaths = {"tickets"})
    Optional<Session> readById(Long id);

    //Optional - makes sure it works even when Session is null (won't receive NullPointerException)
}
