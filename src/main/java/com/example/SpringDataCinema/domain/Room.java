package com.example.SpringDataCinema.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.StringJoiner;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private int capacity;
    private String description;

    public Room(Long id, String name, int capacity, String description) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.description = description;
    }

    public Room() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /* these are best implementations of equals() & hashCode() for entities objects
why? read this: https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room other = (Room) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Room.class.getSimpleName() + " [", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("capacity=" + capacity)
                .add("description='" + description + "'")
                .toString();
    }
}
