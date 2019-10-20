package com.example.SpringDataCinema.boundary;


import com.example.SpringDataCinema.domain.EMovieCategory;
import com.example.SpringDataCinema.domain.Movie;

public class MovieDTO {
    private Long id;
    private String title;
    private EMovieCategory category;
    private Integer length;
    private String description;
    private Integer requiredAge;
    private String posterFilePath;

//    public MovieDTO() { //do I need this here?
//    }

    public MovieDTO(Long id, String title, EMovieCategory category, Integer length, String description, Integer requiredAge, String posterFilePath) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.length = length;
        this.description = description;
        this.requiredAge = requiredAge;
        this.posterFilePath = posterFilePath;
    }

    public Movie createMoviefromMovieDTO() { //creates Movie object
        return new Movie(this.id, this.title, this.category, this.length, this.description, this.requiredAge);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRequiredAge() {
        return requiredAge;
    }

    public void setRequiredAge(Integer requiredAge) {
        this.requiredAge = requiredAge;
    }

    public String getPosterFilePath() {
        return posterFilePath;
    }

    public void setPosterFilePath(String posterFilePath) {
        this.posterFilePath = posterFilePath;
    }
}
