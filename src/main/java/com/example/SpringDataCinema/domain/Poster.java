package com.example.SpringDataCinema.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.StringJoiner;

@Entity
public class Poster {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String filePath;


    public Poster() {
    }

    public Poster(Long id, String filePath) {
        this.id = id;
        this.filePath = filePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /* these are best implementations of equals() & hashCode() for entities objects
    why? read this: https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Poster)) return false;
        Poster other = (Poster) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Poster.class.getSimpleName() + " [", "]")
                .add("id=" + id)
                .add("filePath='" + filePath + "'")
                .toString();
    }
}
