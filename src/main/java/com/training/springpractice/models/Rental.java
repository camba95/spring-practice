package com.training.springpractice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "movie_rental")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Rental {

    @Transient
    private final Integer daysToReturn = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime to_return_date;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime returned_date;

    public Rental() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setMovieId(Long id) {
        Movie movie = new Movie();
        movie.setId(id);
        setMovie(movie);
    }

    public void setMemberId(Long id) {
        Member member = new Member();
        member.setId(id);
        setMember(member);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getTo_return_date() {
        return to_return_date;
    }

    public void setTo_return_date(LocalDateTime to_return_date) {
        this.to_return_date = to_return_date;
    }

    public LocalDateTime getReturned_date() {
        return returned_date;
    }

    public void setReturned_date(LocalDateTime returned_date) {
        this.returned_date = returned_date;
    }

    @PrePersist
    public void setToReturnDate() {
        to_return_date = date.plusDays(daysToReturn);
    }
}
