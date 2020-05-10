package com.training.springpractice.services;

import com.training.springpractice.models.Movie;
import com.training.springpractice.models.MovieDerived;
import com.training.springpractice.repositories.MovieDerivedRepository;
import com.training.springpractice.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("movieService")
public class MovieService {

    @Autowired
    private MovieDerivedRepository movieDerivedRepository;

    @Autowired
    private MovieRepository movieRepository;

    public MovieService() {}

    public List<MovieDerived> findAll() {
        return movieDerivedRepository.findByDeleted(false);
    }

    public Movie create (Movie movie) {
        return movieRepository.saveAndFlush(movie);
    }
}
