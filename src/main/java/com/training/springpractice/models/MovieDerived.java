package com.training.springpractice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.training.springpractice.enums.RateType;

import javax.persistence.*;
import java.util.List;

@Entity(name = "movie_derived")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MovieDerived {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer year;

    @Enumerated(EnumType.STRING)
    private RateType rate;
    private Integer copies;
    private Boolean deleted;

    @OneToMany
    @JoinTable(
            name = "movies_actors",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Actor> actors;

    public MovieDerived() {}

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public RateType getRate() {
        return rate;
    }

    public void setRate(RateType rate) {
        this.rate = rate;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer rate) {
        this.copies = copies;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
