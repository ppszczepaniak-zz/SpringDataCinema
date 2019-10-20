package com.example.SpringDataCinema.service;

import com.example.SpringDataCinema.domain.Marathon;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MarathonService {

    Long createMarathon(String name, LocalDateTime startTime, List<Long> movies);

    Optional<Marathon> getMarathon(Long marathonId);

    List<Marathon> getAllMarathons();

    void removeMarathon(Long marathonId);
}
