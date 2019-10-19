package com.example.SpringDataCinema.repository;

import com.example.SpringDataCinema.domain.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {
}
