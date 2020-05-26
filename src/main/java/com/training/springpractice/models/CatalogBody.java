package com.training.springpractice.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CatalogBody {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "movie_id")
    private Long movie_id;

    @NotNull
    @Column(name = "price_id")
    private Long price_id;

    @NotNull
    @Min(0)
    private Integer copies;

    public CatalogBody() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Long movie_id) {
        this.movie_id = movie_id;
    }

    public Long getPrice_id() {
        return price_id;
    }

    public void setPrice_id(Long price_id) {
        this.price_id = price_id;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }
}
