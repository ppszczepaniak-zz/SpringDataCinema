package com.example.SpringDataCinema.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //generetes ID via Postgres Sequence
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING) //this makes DB store String of Enum, not number (so HORROR, instead of 0, etc.)
    private EMovieCategory category;
    private Integer length;
    private String descritpion;
    private Integer requiredAge;

    public Movie() {
    }

    public Movie(Long id, String title, EMovieCategory category, Integer length, String descritpion, Integer requiredAge) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.length = length;
        this.descritpion = descritpion;
        this.requiredAge = requiredAge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EMovieCategory getCategory() {
        return category;
    }

    public void setCategory(EMovieCategory category) {
        this.category = category;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public Integer getRequiredAge() {
        return requiredAge;
    }

    public void setRequiredAge(Integer requiredAge) {
        this.requiredAge = requiredAge;
    }

    /* these are best implementations of equals() & hashCode() for entities objects
    why? read this: https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return id != null && id.equals(movie.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Movie.class.getSimpleName() + " [", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("category=" + category)
                .add("length=" + length)
                .add("descritpion='" + descritpion + "'")
                .add("requiredAge=" + requiredAge)
                .toString();
    }
}